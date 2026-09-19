package com.ledgerpulse.backend.service.impl;

import com.ledgerpulse.backend.dto.request.TransactionRequestDto;
import com.ledgerpulse.backend.dto.response.TransactionResponseDto;
import com.ledgerpulse.backend.entity.Account;
import com.ledgerpulse.backend.entity.Category;
import com.ledgerpulse.backend.entity.Transaction;
import com.ledgerpulse.backend.entity.User;
import com.ledgerpulse.backend.exception.ResourceNotFoundException;
import com.ledgerpulse.backend.repository.AccountRepository;
import com.ledgerpulse.backend.repository.CategoryRepository;
import com.ledgerpulse.backend.repository.TransactionRepository;
import com.ledgerpulse.backend.repository.UserRepository;
import com.ledgerpulse.backend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public TransactionResponseDto createTransaction(String email, TransactionRequestDto requestDto) {
        User user = getUser(email);
        
        Account account = accountRepository.findByIdAndUserId(requestDto.getAccountId(), user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found or you don't have permission"));

        Category category = getAndValidateCategory(requestDto.getCategoryId(), user.getId(), requestDto.getType());

        Transaction transaction = Transaction.builder()
                .amount(requestDto.getAmount())
                .type(requestDto.getType())
                .notes(requestDto.getNotes())
                .account(account)
                .category(category)
                .build();

        Transaction savedTransaction = transactionRepository.saveAndFlush(transaction);
        return mapToResponseDto(savedTransaction);
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionResponseDto getTransactionById(String email, String transactionId) {
        User user = getUser(email);
        
        Transaction transaction = transactionRepository.findByIdAndAccountUserId(transactionId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found or you don't have permission"));

        return mapToResponseDto(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponseDto> getAllTransactions(String email) {
        User user = getUser(email);
        
        List<Transaction> transactions = transactionRepository.findAllByUserId(user.getId());
        return transactions.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TransactionResponseDto updateTransaction(String email, String transactionId, TransactionRequestDto requestDto) {
        User user = getUser(email);

        Transaction transaction = transactionRepository.findByIdAndAccountUserId(transactionId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found or you don't have permission"));

        Account account = accountRepository.findByIdAndUserId(requestDto.getAccountId(), user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found or you don't have permission"));

        Category category = getAndValidateCategory(requestDto.getCategoryId(), user.getId(), requestDto.getType());

        transaction.setAmount(requestDto.getAmount());
        transaction.setType(requestDto.getType());
        transaction.setNotes(requestDto.getNotes());
        transaction.setAccount(account);
        transaction.setCategory(category);

        Transaction updatedTransaction = transactionRepository.save(transaction);
        return mapToResponseDto(updatedTransaction);
    }

    @Override
    @Transactional
    public void deleteTransaction(String email, String transactionId) {
        User user = getUser(email);

        Transaction transaction = transactionRepository.findByIdAndAccountUserId(transactionId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found or you don't have permission"));

        transactionRepository.delete(transaction);
    }

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Category getAndValidateCategory(String categoryId, String userId, com.ledgerpulse.backend.enums.CategoryType type) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (category.getUser() != null && !category.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You do not have permission to use this category");
        }

        if (category.getType() != type) {
            throw new IllegalArgumentException("Transaction type and category type must match");
        }
        return category;
    }

    private TransactionResponseDto mapToResponseDto(Transaction transaction) {
        return TransactionResponseDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .date(transaction.getDate())
                .notes(transaction.getNotes())
                .createdAt(transaction.getCreatedAt())
                .updatedAt(transaction.getUpdatedAt())
                .accountId(transaction.getAccount().getId())
                .accountName(transaction.getAccount().getName())
                .categoryId(transaction.getCategory().getId())
                .categoryName(transaction.getCategory().getName())
                .categoryType(transaction.getCategory().getType())
                .build();
    }
    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponseDto> filterTransactions(
            String email, String keyword, com.ledgerpulse.backend.enums.CategoryType type, 
            String categoryId, java.time.LocalDateTime startDate, java.time.LocalDateTime endDate) {
        
        User user = getUser(email);

        org.springframework.data.jpa.domain.Specification<Transaction> spec = 
            org.springframework.data.jpa.domain.Specification.where(
                com.ledgerpulse.backend.repository.specification.TransactionSpecification.belongsToUser(user.getId())
            );

        if (keyword != null && !keyword.isBlank()) {
            spec = spec.and(com.ledgerpulse.backend.repository.specification.TransactionSpecification.hasKeyword(keyword));
        }
        if (type != null) {
            spec = spec.and(com.ledgerpulse.backend.repository.specification.TransactionSpecification.hasType(type));
        }
        if (categoryId != null && !categoryId.isBlank()) {
            spec = spec.and(com.ledgerpulse.backend.repository.specification.TransactionSpecification.hasCategory(categoryId));
        }
        if (startDate != null) {
            spec = spec.and(com.ledgerpulse.backend.repository.specification.TransactionSpecification.dateAfterOrEqual(startDate));
        }
        if (endDate != null) {
            spec = spec.and(com.ledgerpulse.backend.repository.specification.TransactionSpecification.dateBeforeOrEqual(endDate));
        }

        List<Transaction> transactions = transactionRepository.findAll(spec);
        return transactions.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }
}

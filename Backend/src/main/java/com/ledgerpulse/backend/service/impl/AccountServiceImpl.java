package com.ledgerpulse.backend.service.impl;

import com.ledgerpulse.backend.dto.request.AccountCreateRequest;
import com.ledgerpulse.backend.dto.request.AccountUpdateRequest;
import com.ledgerpulse.backend.dto.response.AccountResponse;
import com.ledgerpulse.backend.entity.Account;
import com.ledgerpulse.backend.entity.User;
import com.ledgerpulse.backend.exception.ResourceNotFoundException;
import com.ledgerpulse.backend.repository.AccountRepository;
import com.ledgerpulse.backend.repository.TransactionRepository;
import com.ledgerpulse.backend.repository.UserRepository;
import com.ledgerpulse.backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public AccountResponse createAccount(AccountCreateRequest request, String email) {
        User user = getUser(email);

        Account account = Account.builder()
                .name(request.getName())
                .type(request.getType())
                .openingBalance(request.getOpeningBalance())
                .currency(request.getCurrency())
                .isActive(true)
                .user(user)
                .build();

        Account savedAccount = accountRepository.save(account);
        return mapToResponse(savedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> getAllAccounts(String email) {
        User user = getUser(email);
        List<Account> accounts = accountRepository.findByUserId(user.getId());
        return accounts.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponse getAccountById(String id, String email) {
        Account account = getAccountOrThrow(id, email);
        return mapToResponse(account);
    }

    @Override
    @Transactional
    public AccountResponse updateAccount(String id, AccountUpdateRequest request, String email) {
        Account account = getAccountOrThrow(id, email);

        account.setName(request.getName());
        if (request.getIsActive() != null) {
            account.setIsActive(request.getIsActive());
        }

        Account updatedAccount = accountRepository.save(account);
        return mapToResponse(updatedAccount);
    }

    @Override
    @Transactional
    public void deleteAccount(String id, String email) {
        Account account = getAccountOrThrow(id, email);

        if (transactionRepository.existsByAccountId(id)) {
            // Soft delete if there are transactions
            account.setIsActive(false);
            accountRepository.save(account);
        } else {
            // Hard delete if there are no transactions
            accountRepository.delete(account);
        }
    }

    private Account getAccountOrThrow(String id, String email) {
        User user = getUser(email);
        return accountRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private AccountResponse mapToResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .name(account.getName())
                .type(account.getType())
                .openingBalance(account.getOpeningBalance())
                .currency(account.getCurrency())
                .isActive(account.getIsActive())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }
}

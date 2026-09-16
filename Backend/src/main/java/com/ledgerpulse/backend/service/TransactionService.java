package com.ledgerpulse.backend.service;

import com.ledgerpulse.backend.dto.request.TransactionRequestDto;
import com.ledgerpulse.backend.dto.response.TransactionResponseDto;

import java.util.List;

public interface TransactionService {
    TransactionResponseDto createTransaction(String email, TransactionRequestDto requestDto);
    TransactionResponseDto getTransactionById(String email, String transactionId);
    List<TransactionResponseDto> getAllTransactions(String email);
    TransactionResponseDto updateTransaction(String email, String transactionId, TransactionRequestDto requestDto);
    void deleteTransaction(String email, String transactionId);
}

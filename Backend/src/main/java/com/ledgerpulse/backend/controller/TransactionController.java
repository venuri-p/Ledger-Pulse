package com.ledgerpulse.backend.controller;

import com.ledgerpulse.backend.dto.request.TransactionRequestDto;
import com.ledgerpulse.backend.dto.response.TransactionResponseDto;
import com.ledgerpulse.backend.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import com.ledgerpulse.backend.enums.CategoryType;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDto> createTransaction(
            @Valid @RequestBody TransactionRequestDto requestDto,
            Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.createTransaction(email, requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> getTransactionById(
            @PathVariable String id,
            Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(transactionService.getTransactionById(email, id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) CategoryType type,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(transactionService.filterTransactions(email, keyword, type, categoryId, startDate, endDate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> updateTransaction(
            @PathVariable String id,
            @Valid @RequestBody TransactionRequestDto requestDto,
            Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(transactionService.updateTransaction(email, id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable String id,
            Authentication authentication) {
        String email = authentication.getName();
        transactionService.deleteTransaction(email, id);
        return ResponseEntity.noContent().build();
    }
}

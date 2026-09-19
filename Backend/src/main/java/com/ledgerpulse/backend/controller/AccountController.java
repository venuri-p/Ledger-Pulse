package com.ledgerpulse.backend.controller;

import com.ledgerpulse.backend.dto.request.AccountCreateRequest;
import com.ledgerpulse.backend.dto.request.AccountUpdateRequest;
import com.ledgerpulse.backend.dto.response.AccountResponse;
import com.ledgerpulse.backend.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody AccountCreateRequest request,
            Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.createAccount(request, email));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(accountService.getAllAccounts(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(
            @PathVariable String id,
            Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(accountService.getAccountById(id, email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(
            @PathVariable String id,
            @Valid @RequestBody AccountUpdateRequest request,
            Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(accountService.updateAccount(id, request, email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(
            @PathVariable String id,
            Authentication authentication) {
        String email = authentication.getName();
        accountService.deleteAccount(id, email);
        return ResponseEntity.noContent().build();
    }
}

package com.ledgerpulse.backend.service;

import com.ledgerpulse.backend.dto.request.AccountCreateRequest;
import com.ledgerpulse.backend.dto.request.AccountUpdateRequest;
import com.ledgerpulse.backend.dto.response.AccountResponse;

import java.util.List;

public interface AccountService {
    AccountResponse createAccount(AccountCreateRequest request, String email);
    List<AccountResponse> getAllAccounts(String email);
    AccountResponse getAccountById(String id, String email);
    AccountResponse updateAccount(String id, AccountUpdateRequest request, String email);
    void deleteAccount(String id, String email);
}

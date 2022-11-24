package com.example.app.service;

import com.example.app.dto.response.AccountResponse;
import com.example.app.domain.Account;
import com.example.app.dto.request.CreateAccountRequest;

import java.util.List;

public interface AccountService {

    void createAccount(CreateAccountRequest createAccountRequest);

    List<AccountResponse> getAccounts();

    AccountResponse getAccountByAccountNumberAndBranchCode(String accountNumber, String branchCode);

    Account getAccountById(Long id);

    void deleteAccount(Long accountId);
}

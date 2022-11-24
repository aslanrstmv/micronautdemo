package com.example.app.service.impl;

import com.example.app.dto.response.AccountResponse;
import com.example.app.exception.AccountNotFoundException;
import com.example.app.repository.AccountRepository;
import com.example.app.service.AccountService;
import com.example.app.domain.Account;
import com.example.app.dto.request.CreateAccountRequest;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public void createAccount(CreateAccountRequest createAccountRequest) {
        log.info("{}", createAccountRequest);
        Account account = Account.builder()
                .accountNumber(createAccountRequest.getAccountNumber())
                .amount(BigDecimal.ZERO)
                .branchCode(createAccountRequest.getBranchCode())
                .build();
        accountRepository.save(account);
    }

    @Override
    public List<AccountResponse> getAccounts() {
        return accountRepository.findAll()
                .stream().map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponse getAccountByAccountNumberAndBranchCode(String accountNumber, String branchCode) {
        log.info("Get account by account number: {} and branchCode: {}", accountNumber, branchCode);
        Account account = accountRepository.findByAccountNumberAndBranchCode(accountNumber, branchCode)
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account not found with given %s and %s", accountNumber, branchCode)));
        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .amount(account.getAmount())
                .branchCode(account.getBranchCode())
                .build();
    }

    @Override
    public Account getAccountById(Long id) {
        log.info("Get account by id: {}", id);
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account not found with id: %d", id)));
    }

    @Override
    public void deleteAccount(Long accountId) {
        log.info("Delete account with accountId: {}", accountId);
        accountRepository.delete(getAccountById(accountId));
    }

    private AccountResponse mapEntityToDto(Account account) {
        if (account == null) {
            return null;
        }
        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .amount(account.getAmount())
                .branchCode(account.getBranchCode())
                .build();
    }
}

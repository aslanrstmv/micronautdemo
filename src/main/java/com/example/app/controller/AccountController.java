package com.example.app.controller;

import com.example.app.dto.response.AccountResponse;
import com.example.app.dto.request.CreateAccountRequest;
import com.example.app.service.AccountService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Controller("api/v1/accounts")
public class AccountController {

    @Inject
    private final AccountService accountService;

    @Post
    public void createAccount(CreateAccountRequest createAccountRequest) {
        accountService.createAccount(createAccountRequest);
    }

    @Get()
    public List<AccountResponse> getAccounts() {
        return accountService.getAccounts();
    }

    @Get("/{accountNumber}/branches/{branchCode}")
    public AccountResponse getAccount(@PathVariable("accountNumber") String accountNumber, @PathVariable("branchCode") String branchCode) {
        return accountService.getAccountByAccountNumberAndBranchCode(accountNumber, branchCode);
    }

    @Delete(uri = "/{accountId}")
    public HttpResponse<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return HttpResponse.noContent();
    }
}

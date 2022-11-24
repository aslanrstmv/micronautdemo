package com.example.app.repository;

import com.example.app.domain.Account;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumberAndBranchCode(String accountNumber, String branchCode);
}

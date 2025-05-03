package com.catalin.javapersistence.repositories.test;

import com.catalin.javapersistence.models.test.BankAccount;
import com.catalin.javapersistence.models.test.CreditCard;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends BillingDetailsRepository<BankAccount, Long> {

        List<BankAccount> findBySwift(@NotNull String swift);
}
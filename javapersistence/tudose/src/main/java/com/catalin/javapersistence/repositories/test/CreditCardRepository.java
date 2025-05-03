package com.catalin.javapersistence.repositories.test;

import com.catalin.javapersistence.models.test.CreditCard;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends BillingDetailsRepository<CreditCard, Long> {
        
        List<CreditCard> findByExpYear(@NotNull String expYear);
}
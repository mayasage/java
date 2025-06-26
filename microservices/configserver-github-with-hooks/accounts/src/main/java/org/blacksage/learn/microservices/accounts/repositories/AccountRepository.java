package org.blacksage.learn.microservices.accounts.repositories;

import org.blacksage.learn.microservices.accounts.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
        Optional<Account> findByCustomerId(Long customerId);

        @Modifying
        void deleteByCustomerId(Long customerId);
}

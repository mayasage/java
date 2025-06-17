package org.blacksage.learn.microservices.accounts.repositories;

import org.blacksage.learn.microservices.accounts.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
        boolean existsByMobileNumber(String mobileNumber);

        Optional<Customer> findByMobileNumber(String mobileNumber);
}

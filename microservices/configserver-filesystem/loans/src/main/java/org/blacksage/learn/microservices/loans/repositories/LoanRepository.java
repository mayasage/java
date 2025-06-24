package org.blacksage.learn.microservices.loans.repositories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.blacksage.learn.microservices.loans.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
        boolean existsByMobileNumber(
                @NotBlank(message = "Mobile Number can not be a null or empty")
                @Pattern(
                        regexp = "(^$|[0-9]{10})",
                        message = "Mobile Number must be 10 digits"
                )
                String mobileNumber
        );

        Optional<Loan> findByMobileNumber(String mobileNumber);
}

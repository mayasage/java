package org.blacksage.learn.microservices.loans.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "loans")
@Getter
@Setter
public class Loan extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long loanId;

        @NaturalId
        private String mobileNumber;

        private String loanNumber;

        private String loanType;

        private int totalLoan;

        private int amountPaid;

        private int outstandingAmount;
}

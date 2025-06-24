package org.blacksage.learn.microservices.accounts.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account extends BaseEntity {

        @Column(name = "customer_id")
        private Long customerId;

        @Column(name = "account_number", unique = true, nullable = false)
        @Id
        private Long accountNumber;

        @Column(name = "account_type", nullable = false)
        private String accountType;

        @Column(name = "branch_address", nullable = false)
        private String branchAddress;
}

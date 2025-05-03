package com.catalin.javapersistence.models.test;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Entity
@Getter
@Setter
@DiscriminatorValue("ba")
//@SecondaryTable(
//        name = "bank_accounts",
//        pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")
//)
public class BankAccount extends BillingDetails {

//        @NaturalId
        @NotNull
        @Column(unique = true, nullable = false)
//        @Column(table = "bank_accounts", unique = true, nullable = false)
        private String accNumber;

        @NotNull
        @Column(nullable = false)
//        @Column(table = "bank_accounts", nullable = false)
        private String bankName;

//        @NaturalId
        @NotNull
        @Column(unique = true, nullable = false)
//        @Column(table = "bank_accounts", unique = true, nullable = false)
        private String swift;
}

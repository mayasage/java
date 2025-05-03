package com.catalin.javapersistence.models.test;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
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
@DiscriminatorValue("cc")
//@SecondaryTable(
//        name = "credit_cards",
//        pkJoinColumns = @PrimaryKeyJoinColumn(name = "credit_card_id")
//)
public class CreditCard extends BillingDetails {

//        @NaturalId
        @NotNull
        @Column(unique = true, nullable = false)
//        @Column(table = "credit_cards", unique = true, nullable = false)
        private String ccNumber;

        @NotNull
        @Column(nullable = false)
//        @Column(table = "credit_cards", nullable = false)
        private String expMonth;

        @NotNull
        @Column(nullable = false)
//        @Column(table = "credit_cards", nullable = false)
        private String expYear;

        @Override
        public String toString() {
                return "CreditCard{" +
                       "ccNumber='" + ccNumber + '\'' +
                       ", expMonth='" + expMonth + '\'' +
                       ", expYear='" + expYear + '\'' +
                       '}';
        }
}

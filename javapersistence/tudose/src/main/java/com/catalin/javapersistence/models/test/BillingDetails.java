package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorFormula;

@Entity
@Getter
@Setter
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "bd_type")
//@DiscriminatorFormula(
//        "CASE WHEN cc_number IS NULL THEN 'ba' ELSE 'cc' END"
//)
@Table(name = "billing_details")
public abstract class BillingDetails extends AbstractEntityBase {

        @ManyToOne
        private User user;

        @NotNull
        @Column(nullable = false)
        private String owner;

        @Override
        public String toString() {
                return "BillingDetails{" +
                       "user=" + user +
                       ", owner='" + owner + '\'' +
                       '}';
        }
}

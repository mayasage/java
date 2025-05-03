package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class Bid1 extends AbstractEntityBase {

        @NotNull
        @Getter
        @Setter
        private BigDecimal amount;

        @Getter
        @Setter
        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "ITEM_ID")
        private Item1 item;

        public Bid1(BigDecimal amount, Item1 item) {
                this.amount = amount;
                this.item = item;
                item.addBid(this);
        }
}

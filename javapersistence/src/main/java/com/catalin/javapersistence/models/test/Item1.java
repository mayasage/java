package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item1 extends AbstractEntityBase {

        @NotNull
        @Size(
                min = 2,
                max = 255,
                message = "Name is required, maximum 255 characters."
        )
        @Getter
        @Setter
        private String name;

        @Transient
        private Set<Bid1> bids = new HashSet<>();

        public Set<Bid1> getBids() {
                return Collections.unmodifiableSet(bids);
        }

        public void addBid(Bid1 bid) {
                bids.add(bid);
        }
}

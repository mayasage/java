package com.catalin.javapersistence.models.auction;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "auction_items")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "auction_item_id", nullable = false, updatable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "auction_item_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "auction_item_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "auction_item_updated_at", nullable = false)),
})
@Getter
@Setter
public class AuctionItem extends AbstractEntityBase {

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "auctionItem",
                targetEntity = AuctionLot.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<AuctionLot> auctionLots = new HashSet<>();

        @NotBlank
        @Column(nullable = false)
        private String auctionItemName;

        @NotNull
        @Column(nullable = false)
        private BigDecimal auctionItemInitialPrice;

        @Column(nullable = false)
        private String auctionItemDescription;

        public Set<AuctionLot> getAuctionLots() {
                return Collections.unmodifiableSet(auctionLots);
        }

        public Set<AuctionEvent> getAuctionEvents() {
                return auctionLots.stream().map(AuctionLot::getAuctionEvent).collect(Collectors.toUnmodifiableSet());
        }

        @PackagePrivate
        void addAuctionLot(@NotNull AuctionLot auctionLot) {
                auctionLots.add(auctionLot);
        }

        @PrePersist
        private void prePersist() {
                setAuctionItemDescription(auctionItemDescription == null ? "" : auctionItemDescription);
        }

        @PreUpdate
        private void preUpdate() {
                setAuctionItemDescription(auctionItemDescription == null ? "" : auctionItemDescription);
        }
}

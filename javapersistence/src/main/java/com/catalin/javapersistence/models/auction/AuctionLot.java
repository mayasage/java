package com.catalin.javapersistence.models.auction;

import com.catalin.javapersistence.models.auction.enums.AuctionLotStatusEnum;
import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "auction_lots",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"auction_item_id", "auction_event_id"}
        )
)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "auction_lot_id", nullable = false, updatable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "auction_lot_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "auction_lot_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "auction_lot_updated_at", nullable = false)),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public class AuctionLot extends AbstractEntityBase {

        @OneToMany(
                mappedBy = "auctionLot",
                targetEntity = AuctionBid.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private Set<AuctionBid> auctionBids = new HashSet<>();

        @ManyToOne(
                fetch = FetchType.LAZY,
                optional = false
        )
        @JoinColumn(name = "auction_item_id", nullable = false)
        private AuctionItem auctionItem;

        @ManyToOne(
                fetch = FetchType.LAZY,
                optional = false
        )
        @JoinColumn(name = "auction_event_id", nullable = false)
        private AuctionEvent auctionEvent;

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private AuctionLotStatusEnum auctionLotStatus = AuctionLotStatusEnum.AUCTION_LOT_IS_CREATED;


}

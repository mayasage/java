package com.catalin.javapersistence.models.auction;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(
        name = "auction_bids",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"auction_participant_id", "auction_lot_id"}
        )
)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "auction_bid_id", nullable = false, updatable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "auction_bid_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "auction_bid_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "auction_bid_updated_at", nullable = false)),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public class AuctionBid extends AbstractEntityBase {

        @ManyToOne(
                targetEntity = AuctionParticipant.class,
                optional = false,
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "auction_participant_id", nullable = false)
        private AuctionParticipant auctionParticipant;

        @ManyToOne(
                targetEntity = AuctionLot.class,
                optional = false,
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "auction_lot_id", nullable = false)
        private AuctionLot auctionLot;

        private BigDecimal auctionBidAmount;

//        public ZonedDateTime getAuctionBidCreatedAt() {
//                return super.getCreatedAt();
//        }
}

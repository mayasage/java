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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "auction_events")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "auction_event_id", nullable = false, updatable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "auction_event_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "auction_event_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "auction_event_updated_at", nullable = false)),
})
@Getter
@Setter
public class AuctionEvent extends AbstractEntityBase {

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "auctionEvent",
                targetEntity = AuctionLot.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<AuctionLot> auctionLots = new HashSet<>();

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "auctionEvent",
                targetEntity = AuctionParticipant.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<AuctionParticipant> auctionParticipants = new HashSet<>();

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "auctionEvent",
                targetEntity = AuctionEventParticipantRole.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<AuctionEventParticipantRole> auctionEventParticipantRoles = new HashSet<>();

        @NotBlank
        @Column(nullable = false)
        private String auctionEventName;

        @Column(nullable = false)
        private String auctionEventDescription;

        public void addAuctionParticipant(AuctionParticipant auctionParticipant) {
                auctionParticipants.add(auctionParticipant);
        }

        @PrePersist
        private void prePersist() {
                if (auctionEventDescription == null) auctionEventDescription = "";
        }

        @PreUpdate
        private void preUpdate() {
                if (auctionEventDescription == null) auctionEventDescription = "";
        }
}

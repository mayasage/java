package com.catalin.javapersistence.models.auction;

import com.catalin.javapersistence.models.appuser.ApplicationUser;
import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "auction_participants",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"application_user_id", "auction_event_id"}
        )
)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "auction_participant_id", nullable = false, updatable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "auction_participant_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "auction_participant_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "auction_participant_updated_at", nullable = false)),
})
@Getter
@Setter
public class AuctionParticipant extends AbstractEntityBase {

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "auctionParticipant",
                targetEntity = AuctionParticipantEventRoleAssignment.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<AuctionParticipantEventRoleAssignment> auctionParticipantEventRoleAssignments = new HashSet<>();

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "auctionParticipant",
                targetEntity = AuctionBid.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<AuctionBid> auctionBids = new HashSet<>();

        @NotNull
        @ManyToOne(
                targetEntity = ApplicationUser.class,
                optional = false,
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "application_user_id", nullable = false)
        private ApplicationUser applicationUser;

        @NotNull
        @ManyToOne(
                targetEntity = AuctionEvent.class,
                optional = false,
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "auction_event_id", nullable = false)
        private AuctionEvent auctionEvent;

        public void assignAuctionEventParticipantRole(AuctionEventParticipantRole auctionEventParticipantRole) {
                AuctionParticipantEventRoleAssignment auctionParticipantEventRoleAssignment = new AuctionParticipantEventRoleAssignment();
                auctionParticipantEventRoleAssignment.setAuctionEventParticipantRole(auctionEventParticipantRole);
                auctionParticipantEventRoleAssignment.setAuctionParticipant(this);

                auctionEventParticipantRole.addAuctionParticipantEventRoleAssignment(auctionParticipantEventRoleAssignment);
                auctionParticipantEventRoleAssignments.add(auctionParticipantEventRoleAssignment);
        }
}

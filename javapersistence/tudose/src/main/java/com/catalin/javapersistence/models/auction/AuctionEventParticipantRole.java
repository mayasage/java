package com.catalin.javapersistence.models.auction;

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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "auction_event_participant_roles")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "auction_event_participant_role_id", nullable = false, updatable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "auction_event_participant_role_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "auction_event_participant_role_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "auction_event_participant_role_updated_at", nullable = false)),
})
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuctionEventParticipantRole extends AbstractEntityBase {

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "auctionEventParticipantRole",
                targetEntity = AuctionParticipantEventRoleAssignment.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<AuctionParticipantEventRoleAssignment> auctionParticipantEventRoleAssignments = new HashSet<>();

        @ManyToOne(
                targetEntity = AuctionEvent.class,
                optional = false,
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "auction_event_id", nullable = false)
        private AuctionEvent auctionEvent;

        @ManyToOne(
                targetEntity = AuctionParticipantRole.class,
                optional = false,
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "auction_participant_role_id", nullable = false)
        private AuctionParticipantRole auctionParticipantRole;

        protected void addAuctionParticipantEventRoleAssignment(AuctionParticipantEventRoleAssignment auctionParticipantEventRoleAssignment) {
                this.auctionParticipantEventRoleAssignments.add(auctionParticipantEventRoleAssignment);
        }
}

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
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "auction_participant_event_role_assignments",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"auction_participant_id", "auction_event_participant_role_id"}
        )
)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "auction_participant_event_role_assignment_id", nullable = false, updatable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "auction_participant_event_role_assignment_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "auction_participant_event_role_assignment_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "auction_participant_event_role_assignment_updated_at", nullable = false)),
})
@Getter
@Setter
public class AuctionParticipantEventRoleAssignment extends AbstractEntityBase {

        @NotNull
        @ManyToOne(
                targetEntity = AuctionParticipant.class,
                optional = false,
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "auction_participant_id", nullable = false)
        private AuctionParticipant auctionParticipant;

        @NotNull
        @ManyToOne(
                targetEntity = AuctionEventParticipantRole.class,
                optional = false,
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "auction_event_participant_role_id", nullable = false)
        private AuctionEventParticipantRole auctionEventParticipantRole;
}

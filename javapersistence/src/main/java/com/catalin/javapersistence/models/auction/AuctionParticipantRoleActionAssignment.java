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

@Entity
@Table(
        name = "auction_participant_role_action_assignments",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"auction_participation_role_id", "auction_participation_action_id"}
        )
)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "auction_participant_role_action_assignment_id", nullable = false, updatable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "auction_participant_role_action_assignment_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "auction_participant_role_action_assignment_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "auction_participant_role_action_assignment_updated_at", nullable = false)),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public class AuctionParticipantRoleActionAssignment extends AbstractEntityBase {

        @ManyToOne(
                targetEntity = AuctionParticipantRole.class,
                optional = false,
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "auction_participation_role_id", nullable = false)
        private AuctionParticipantRole auctionParticipantRole;

        @ManyToOne(
                targetEntity = AuctionParticipantAction.class,
                optional = false,
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "auction_participation_action_id", nullable = false)
        private AuctionParticipantAction auctionParticipantAction;
}

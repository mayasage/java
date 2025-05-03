package com.catalin.javapersistence.models.auction;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "auction_participant_roles")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "auction_participant_role_id", nullable = false, updatable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "auction_participant_role_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "auction_participant_role_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "auction_participant_role_updated_at", nullable = false)),
})
@Getter
@Setter
public class AuctionParticipantRole extends AbstractEntityBase {

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "auctionParticipantRole",
                targetEntity = AuctionParticipantRoleActionAssignment.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<AuctionParticipantRoleActionAssignment> auctionParticipantRoleActionAssignments = new HashSet<>();

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "auctionParticipantRole",
                targetEntity = AuctionEventParticipantRole.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<AuctionEventParticipantRole> auctionEventParticipantRoles = new HashSet<>();

        @NaturalId
        @NotBlank
        @Column(unique = true, nullable = false)
        private String auctionParticipantRoleName;

        @NotNull
        @Column(nullable = false, updatable = false)
        private Boolean isCustom = false;

        public void assignAuctionParticipantAction(AuctionParticipantAction auctionParticipantAction) {
                AuctionParticipantRoleActionAssignment auctionParticipantRoleActionAssignment = new AuctionParticipantRoleActionAssignment();
                auctionParticipantRoleActionAssignment.setAuctionParticipantRole(this);
                auctionParticipantRoleActionAssignment.setAuctionParticipantAction(auctionParticipantAction);

                auctionParticipantAction.addAuctionParticipantRoleAction(auctionParticipantRoleActionAssignment);
                auctionParticipantRoleActionAssignments.add(auctionParticipantRoleActionAssignment);
        }
}

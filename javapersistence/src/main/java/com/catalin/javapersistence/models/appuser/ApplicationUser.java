package com.catalin.javapersistence.models.appuser;

import com.catalin.javapersistence.models.auction.AuctionParticipant;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

@Entity
@Table(name = "application_users")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "application_user_id", nullable = false, updatable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "application_user_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "application_user_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "application_user_updated_at", nullable = false)),
})
@Getter
@Setter
public class ApplicationUser extends AbstractEntityBase {

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "applicationUser",
                targetEntity = AuctionParticipant.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<AuctionParticipant> auctionParticipants = new HashSet<>();

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "applicationUser",
                targetEntity = ApplicationUserRoleAssignment.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<ApplicationUserRoleAssignment> applicationUserRoleAssignments = new HashSet<>();

        @NotBlank
        @NaturalId
        @Column(unique = true, nullable = false, updatable = false, length = 20)
        private String applicationUserUsername;

        @Email
        @Column(unique = true, nullable = false)
        private String applicationUserEmail;

        @NotBlank
        @Column(nullable = false, length = 30)
        private String applicationUserFirstName;

        @Column(nullable = false, length = 50)
        private String applicationUserLastName;

        public void setApplicationUserFullName(String applicationUserFullName) {
                StringTokenizer stringTokens = new StringTokenizer(applicationUserFullName, " ");
                this.setApplicationUserFirstName(stringTokens.nextToken());

                if (stringTokens.hasMoreTokens()) {
                        StringBuilder userLastNameBuilder = new StringBuilder();
                        userLastNameBuilder.append(stringTokens.nextToken());

                        while (stringTokens.hasMoreTokens()) {
                                userLastNameBuilder.append(" ");
                                userLastNameBuilder.append(stringTokens.nextToken());
                        }

                        this.setApplicationUserLastName(userLastNameBuilder.toString());
                }
        }

        public void assignUserRole(ApplicationUserRole applicationUserRole) {
                ApplicationUserRoleAssignment applicationUserRoleAssignment = new ApplicationUserRoleAssignment();
                applicationUserRoleAssignment.setApplicationUser(this);
                applicationUserRoleAssignment.setApplicationUserRole(applicationUserRole);

                applicationUserRole.addApplicationUserRoleAssignment(applicationUserRoleAssignment);
                applicationUserRoleAssignments.add(applicationUserRoleAssignment);
        }

        public void addAsAuctionParticipant(AuctionParticipant auctionParticipant) {
                auctionParticipants.add(auctionParticipant);
        }

        @PrePersist
        private void prePersist() {
                if (this.applicationUserLastName == null) this.setApplicationUserLastName("");
        }

        @PreUpdate
        private void preUpdate() {
                if (this.applicationUserLastName == null) this.setApplicationUserLastName("");
        }
}

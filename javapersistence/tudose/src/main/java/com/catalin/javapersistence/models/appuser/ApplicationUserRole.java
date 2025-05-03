package com.catalin.javapersistence.models.appuser;

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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "application_user_roles")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "application_user_role_id", nullable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "application_user_role_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "application_user_role_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "application_user_role_updated_at", nullable = false)),
})
@Getter
@Setter
public class ApplicationUserRole extends AbstractEntityBase {

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "applicationUserRole",
                targetEntity = ApplicationUserRoleActionAssignment.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<ApplicationUserRoleActionAssignment> applicationUserRoleActionAssignments = new HashSet<>();

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(
                mappedBy = "applicationUserRole",
                targetEntity = ApplicationUserRoleAssignment.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<ApplicationUserRoleAssignment> applicationUserRoleAssignments = new HashSet<>();

        @NaturalId
        @NotBlank
        @Column(unique = true, nullable = false)
        private String applicationUserRoleName;

        protected void addApplicationUserRoleAssignment(ApplicationUserRoleAssignment applicationUserRoleAssignment) {
                applicationUserRoleAssignments.add(applicationUserRoleAssignment);
        }

        public void assignApplicationUserAction(ApplicationUserAction applicationUserAction) {
                ApplicationUserRoleActionAssignment applicationUserRoleActionAssignment = new ApplicationUserRoleActionAssignment();
                applicationUserRoleActionAssignment.setApplicationUserRole(this);
                applicationUserRoleActionAssignment.setApplicationUserAction(applicationUserAction);

                applicationUserAction.addApplicationUserRoleAction(applicationUserRoleActionAssignment);
                applicationUserRoleActionAssignments.add(applicationUserRoleActionAssignment);
        }
}

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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "application_user_actions")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "application_user_action_id", nullable = false, updatable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "application_user_action_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "application_user_action_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "application_user_action_updated_at", nullable = false)),
})
@Getter
@Setter
public class ApplicationUserAction extends AbstractEntityBase {

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @LazyCollection(LazyCollectionOption.FALSE)
        @OneToMany(
                mappedBy = "applicationUserAction",
                targetEntity = ApplicationUserRoleActionAssignment.class,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private final Set<ApplicationUserRoleActionAssignment> applicationUserRoleActionAssignments = new HashSet<>();

        @NaturalId
        @NotBlank
        @Column(unique = true, nullable = false)
        private String applicationUserActionName;

        protected void addApplicationUserRoleAction(ApplicationUserRoleActionAssignment applicationUserRoleActionAssignment) {
                applicationUserRoleActionAssignments.add(applicationUserRoleActionAssignment);
        }
}

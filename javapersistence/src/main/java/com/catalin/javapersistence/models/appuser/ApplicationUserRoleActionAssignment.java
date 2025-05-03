package com.catalin.javapersistence.models.appuser;

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
        name = "application_user_role_action_assignments",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"application_user_role_id", "application_user_action_id"}
        )
)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "application_user_role_action_assignment_id", nullable = false, updatable = false)),
        @AttributeOverride(name = "version", column = @Column(name = "application_user_role_action_assignment_row_version", nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column(name = "application_user_role_action_assignment_created_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "application_user_role_action_assignment_updated_at", nullable = false)),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public class ApplicationUserRoleActionAssignment extends AbstractEntityBase {

        @ManyToOne(
                targetEntity = ApplicationUserRole.class,
                optional = false,
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "application_user_role_id", nullable = false)
        private ApplicationUserRole applicationUserRole;

        @ManyToOne(
                targetEntity = ApplicationUserAction.class,
                optional = false,
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "application_user_action_id", nullable = false)
        private ApplicationUserAction applicationUserAction;
}

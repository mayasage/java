package com.catalin.javapersistence.repositories.appuser;

import com.catalin.javapersistence.models.appuser.ApplicationUserRole;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRoleRepository extends JpaRepository<ApplicationUserRole, Long> {
        ApplicationUserRole findByApplicationUserRoleName(@NotBlank String applicationUserRoleName);
}
package com.catalin.javapersistence.repositories.appuser;

import com.catalin.javapersistence.models.appuser.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
}
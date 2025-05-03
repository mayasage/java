package com.catalin.javapersistence.repositories.appuser;

import com.catalin.javapersistence.models.appuser.ApplicationUserAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserActionRepository extends JpaRepository<ApplicationUserAction, Long> {
}
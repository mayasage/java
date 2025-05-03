package com.catalin.javapersistence.repositories.test;

import com.catalin.javapersistence.models.test.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
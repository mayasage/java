package com.catalin.javapersistence.repositories.test;

import com.catalin.javapersistence.models.test.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long>, LogRepositoryCustom {
}
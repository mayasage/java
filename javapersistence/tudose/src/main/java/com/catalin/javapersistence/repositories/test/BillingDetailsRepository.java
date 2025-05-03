package com.catalin.javapersistence.repositories.test;

import com.catalin.javapersistence.models.test.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingDetailsRepository<T extends BillingDetails, ID> extends JpaRepository<T, ID> {

        List<T> findByOwner(String owner);
}
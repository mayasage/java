package com.catalin.javapersistence.repositories.test;

import com.catalin.javapersistence.models.test.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
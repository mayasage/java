package com.catalin.javapersistence.repositories.test;

import com.catalin.javapersistence.models.test.Bid;
import com.catalin.javapersistence.models.test.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
//        Set<Bid> findByItem(Item item);
}
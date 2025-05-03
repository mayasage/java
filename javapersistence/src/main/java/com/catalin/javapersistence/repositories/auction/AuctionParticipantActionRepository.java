package com.catalin.javapersistence.repositories.auction;

import com.catalin.javapersistence.models.auction.AuctionParticipantAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionParticipantActionRepository extends JpaRepository<AuctionParticipantAction, Long> {
}
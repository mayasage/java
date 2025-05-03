package com.catalin.javapersistence.repositories.auction;

import com.catalin.javapersistence.models.auction.AuctionParticipantRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionParticipantRoleRepository extends JpaRepository<AuctionParticipantRole, Long> {
}
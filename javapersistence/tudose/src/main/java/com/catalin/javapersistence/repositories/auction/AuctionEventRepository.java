package com.catalin.javapersistence.repositories.auction;

import com.catalin.javapersistence.models.auction.AuctionEvent;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionEventRepository extends JpaRepository<AuctionEvent, Long> {
  AuctionEvent findAuctionEventByAuctionEventName(@NotBlank String auctionEventName);
}
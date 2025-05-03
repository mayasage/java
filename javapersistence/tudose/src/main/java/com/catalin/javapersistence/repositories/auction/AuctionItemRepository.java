package com.catalin.javapersistence.repositories.auction;

import com.catalin.javapersistence.models.auction.AuctionItem;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {
  AuctionItem findAuctionItemByAuctionItemName(@NotBlank String auctionItemName);
}
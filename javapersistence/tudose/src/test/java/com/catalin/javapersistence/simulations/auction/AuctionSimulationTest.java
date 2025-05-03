package com.catalin.javapersistence.simulations.auction;

import com.catalin.javapersistence.models.auction.AuctionEvent;
import com.catalin.javapersistence.models.auction.AuctionItem;
import com.catalin.javapersistence.repositories.auction.AuctionEventRepository;
import com.catalin.javapersistence.repositories.auction.AuctionItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@Transactional
public class AuctionSimulationTest {

        @PersistenceContext
        private EntityManager entityManager;

        @Autowired
        private AuctionItemRepository auctionItemRepository;

        @Autowired
        private AuctionEventRepository auctionEventRepository;

        @AfterEach
        public void afterEach() {
                AuctionItem elixir = auctionItemRepository.findAuctionItemByAuctionItemName("Elixir");
                AuctionItem potion = auctionItemRepository.findAuctionItemByAuctionItemName("Potion");
                AuctionItem phoenixDown = auctionItemRepository.findAuctionItemByAuctionItemName("Phoenix Down");

                AuctionEvent auctionEvent = auctionEventRepository.findAuctionEventByAuctionEventName("Red Dragon Auction");

                Assertions.assertAll(
                        () -> Assertions.assertNotNull(elixir),
                        () -> Assertions.assertNotNull(potion),
                        () -> Assertions.assertNotNull(phoenixDown),
                        () -> Assertions.assertNotNull(auctionEvent),
//                        () -> Assertions.assertEquals(3, auctionEvent.getAuctionLots().size()),
//                        () -> Assertions.assertEquals(3, auctionEvent.getAuctionItems().size()),
                        () -> Assertions.assertEquals(1, elixir.getAuctionEvents().size()),
                        () -> Assertions.assertEquals(1, potion.getAuctionEvents().size()),
                        () -> Assertions.assertEquals(1, phoenixDown.getAuctionEvents().size()),
                        () -> Assertions.assertEquals(1, elixir.getAuctionLots().size()),
                        () -> Assertions.assertEquals(1, potion.getAuctionLots().size()),
                        () -> Assertions.assertEquals(1, phoenixDown.getAuctionLots().size())
                );

                auctionEventRepository.delete(auctionEvent);
                auctionItemRepository.delete(elixir);
        }

        @Test
        public void simulateAuctionProcess() {

        }

        @BeforeEach
        public void beforeEach() {
                // --- create auction items ---
                AuctionItem elixir = new AuctionItem();
                elixir.setAuctionItemName("Elixir");
                elixir.setAuctionItemInitialPrice(BigDecimal.valueOf(10));
                elixir.setAuctionItemDescription("Heals your MP & HP by 50% of your Max HP");
                auctionItemRepository.save(elixir);

                AuctionItem potion = new AuctionItem();
                potion.setAuctionItemName("Potion");
                potion.setAuctionItemInitialPrice(BigDecimal.valueOf(5));
                potion.setAuctionItemDescription("Heals your HP by 50% of your Max HP");
                auctionItemRepository.save(potion);

                AuctionItem phoenixDown = new AuctionItem();
                phoenixDown.setAuctionItemName("Phoenix Down");
                phoenixDown.setAuctionItemInitialPrice(BigDecimal.valueOf(20));
                phoenixDown.setAuctionItemDescription("Revives you with 30% of your Max HP");
                auctionItemRepository.save(phoenixDown);

                // --- create auction events ---
                AuctionEvent auctionEvent = new AuctionEvent();
                auctionEvent.setAuctionEventName("Red Dragon Auction");
                auctionEventRepository.save(auctionEvent);

//                auctionEvent.addAuctionItem(elixir);
//                auctionEvent.addAuctionItem(potion);
//                auctionEvent.addAuctionItem(phoenixDown);
        }
}

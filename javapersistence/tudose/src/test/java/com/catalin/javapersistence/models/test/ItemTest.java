package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.repositories.test.BidRepository;
import com.catalin.javapersistence.repositories.test.ItemRepository;
import com.catalin.javapersistence.repositories.test.ShipmentRepository;
import com.catalin.javapersistence.repositories.test.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemTest {

        @Autowired
        private ItemRepository itemRepository;
        @Autowired
        private BidRepository bidRepository;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private ShipmentRepository shipmentRepository;

        @AfterEach
        @Transactional
        @Commit
        public void tearDown() {
                System.out.println("tearDown method called");

                Item item = itemRepository.findAll().getFirst();
                System.out.println("item: " + item);

                System.out.println("I am adding new bids to existing collection, it should not fetch the collection " +
                                   "first.");

                Bid bid1 = new Bid(BigDecimal.valueOf(100), item);
                Bid bid2 = new Bid(BigDecimal.valueOf(200), item);
                item.addBid(bid1);
                item.addBid(bid2);

//                itemRepository.save(item);

//                item.clearBids();
//                System.out.println("item after clearing: " + item);
                itemRepository.save(item);
        }

        @Test
        @Transactional
        @Commit
        public void createItem() {
                Item newItem = new Item();
                newItem.setItemName("new item");
                newItem.setMetricWeight(new BigDecimal(2));
                newItem.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                newItem.addImage(new Filename("Background"), new Image("background.jpg", 640, 480));
                newItem.addImage(new Filename("Foreground"), new Image("foreground.jpg", 800, 600));
                newItem.addImage(new Filename("Landscape"), new Image("landscape.jpg", 1024, 768));
                newItem.addImage(new Filename("Landscape"), new Image("landscape.jpg", 1024, 768));

                Bid bid = new Bid(BigDecimal.valueOf(100), newItem);
                Bid bid2 = new Bid(BigDecimal.valueOf(200), newItem);
                newItem.addBid(bid);
                newItem.addBid(bid2);

                itemRepository.save(newItem);

                Item refetchNewItem = itemRepository.findItemByItemName(newItem.getItemName());
                System.out.println(refetchNewItem);
                Assertions.assertNotNull(refetchNewItem);

                System.out.println("I am going to refetch, watch the query");

                Item item = itemRepository.findItemWithImages(newItem.getId());
                System.out.println("Item: " + item);
//                System.out.println("first: " + item.getImages().stream().findFirst());
                List<Item> items2 = itemRepository.findAll();
//                Set<Image> images = itemRepository.findImagesNative(item.getId());
//                System.out.println("images: " + images);
//                Set<Bid> bids = bidRepository.findByItem(item);
//                Assertions.assertAll(
//                        () -> assertEquals(3, item.getImages().size()),
//                        () -> assertEquals(1, items2.size()),
////                        () -> assertEquals(4, images.size())
//                        () -> assertEquals(2, bids.size())
//                );

                System.out.println("Alright, I'm deleting item. Watch the delete query.");

                System.out.println("Bidmap: " + items2.getFirst().getBids());

                assertEquals(2, items2.getFirst().getBids().size());
                for (Map.Entry<Long, Bid> entry : items2.getFirst().getBids().entrySet()) {
                        assertEquals(entry.getKey(), entry.getValue().getId());
                }

//                item.clearBids();
//                itemRepository.save(item);
//                itemRepository.delete(item);
//
//                List<Item> items3 = itemRepository.findAll();
//                Set<Bid> bids2 = bidRepository.findByItem(item);
//
//                assertAll(
//                        () -> assertEquals(0, items3.size()),
//                        () -> assertEquals(0, bids2.size())
//                );
        }

        @Test
        @Transactional
        @Commit
        public void testBag() {
                Item item = new Item();

                item.setItemName("new item");
                item.setMetricWeight(new BigDecimal(2));
                item.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));

                Bid bid = new Bid(BigDecimal.valueOf(100), item);
                Bid bid2 = new Bid(BigDecimal.valueOf(200), item);
                item.addBid(bid);
                item.addBid(bid2);

                itemRepository.save(item);

                Item item2 = itemRepository.findItemWithBids(item.getId());
                System.out.println("item2: " + item2);
                assertEquals(2, item2.getBids().size());
        }

        @Test
        @Transactional
        @Commit
        public void userBoughtItems() {
                Item item1 = new Item();

                item1.setItemName("new item1");
                item1.setMetricWeight(new BigDecimal(2));
                item1.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));

                Item item2 = new Item();

                item2.setItemName("new item2");
                item2.setMetricWeight(new BigDecimal(2));
                item2.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));

//                itemRepository.save(item1);
//                itemRepository.save(item2);

                Address address =
                        new Address("Flowers Street", "01246", "Boston");

                User user = new User();
                user.setUsername("username");
                user.setShippingAddress(address);
                user.purchaseItem(item1);
                item1.setBuyer(user);
                user.purchaseItem(item2);
                item2.setBuyer(user);

                userRepository.save(user);
        }
}

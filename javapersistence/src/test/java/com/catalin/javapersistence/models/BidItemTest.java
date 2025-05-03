package com.catalin.javapersistence.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class BidItemTest {

        @PersistenceUnit
        private EntityManagerFactory entityManagerFactory;

//        @Test
//        public void setAccessBidItemHappyFlow() {
//                EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//                entityManager.getTransaction().begin();
//                Item item = new Item("Elixir");
//                item.setBidItemDescription("Heals your MP & HP by 50% of your Max HP");
//                item.setBidItemInitialPrice(BigDecimal.valueOf(10));
//                entityManager.persist(item);
//                entityManager.getTransaction().commit();
//
//                entityManager.getTransaction().begin();
//                Bid bid1 = new Bid(item, BigDecimal.valueOf(20));
//                entityManager.persist(bid1);
//
//                Bid bid2 = new Bid(item, BigDecimal.valueOf(30));
//                entityManager.persist(bid2);
//
//                Bid bid3 = new Bid(item, BigDecimal.valueOf(40));
//                entityManager.persist(bid3);
//                entityManager.getTransaction().commit();
//
//                List<Bid> bids = entityManager.createNamedQuery("findBidsByCreatedAtAscending", Bid.class).getResultList();
//
//                Assertions.assertEquals(3, bids.size());
//                bids.forEach(bid -> Assertions.assertEquals("Elixir", bid.getItem().getBidItemName()));
//        }
}

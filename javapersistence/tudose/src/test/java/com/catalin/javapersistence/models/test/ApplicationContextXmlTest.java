package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.DatabaseService;
import com.catalin.javapersistence.configuration.ProjectConfiguration;
import com.catalin.javapersistence.dao.Bid1Dao;
import com.catalin.javapersistence.dao.GenericDao;
import com.catalin.javapersistence.dao.Item1Dao;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class ApplicationContextXmlTest {

        @Autowired
        private DatabaseService databaseService;

//        @Autowired
//        private GenericDao<Item1> itemDao;

        @Autowired
        private Item1Dao itemDao;

//        @Autowired
//        private GenericDao<Bid1> bidDao;

        @Autowired
        private Bid1Dao bidDao;

        @Autowired
        private EntityManagerFactory emf;

        @BeforeEach
        public void init() {
                databaseService.init();
        }

        @AfterEach
        public void destruct() {
                databaseService.clear();
        }

        @Test
        public void testInsertItems() {
                List<Item1> items = itemDao.getAll();
                List<Bid1> bids = bidDao.getAll();

                assertAll(
                        () -> Assertions.assertEquals(10, items.size()),
                        () -> Assertions.assertEquals(20, bids.size())
                );
        }

//        @Test
//        public void testDeleteItem() {
//                itemDao.delete(itemDao.findByProperty("name", "Item 1").getFirst());
//

        /// /                Assertions.assertThrows(
        /// /                        NoResultException.class,
        /// /                        () -> itemDao.delete(itemDao.findByProperty("name", "Item 1").getFirst())
        /// /                );
//
//                Assertions.assertEquals(0, itemDao.findByProperty("name", "Item 1").size());
//        }
        @Test
        public void testDeleteItem() {
                itemDao.delete(itemDao.findByName("Item 1"));
                Assertions.assertNull(itemDao.findByName("Item 1"));
        }

        @Test
        public void testInsertBid() {
                Item1 item3 = itemDao.findByName("Item 3");
                Bid1 newBid = new Bid1(new BigDecimal("2000.00"), item3);
                bidDao.insert(newBid);
                Assertions.assertAll(
                        () -> Assertions.assertEquals(new BigDecimal("2000.00"), bidDao.getById(newBid.getId()).getAmount()),
                        () -> Assertions.assertEquals(21, bidDao.getAll().size()));
        }

        @Test
        public void testUpdateBid() {
                Bid1 bid = bidDao.findByAmount("1000.00").getFirst();
                bidDao.update(bid.getId(), "1200.00");
                Assertions.assertEquals(new BigDecimal("1200.00"), bidDao.getById(bid.getId()).getAmount());
        }

        @Test
        public void testDeleteBid() {
                Bid1 bid = bidDao.findByAmount("1000.00").getFirst();
                bidDao.delete(bid);
                Assertions.assertEquals(19, bidDao.getAll().size());
        }
}

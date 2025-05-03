package com.catalin.javapersistence;

import com.catalin.javapersistence.dao.GenericDao;
import com.catalin.javapersistence.dao.Item1Dao;
import com.catalin.javapersistence.models.test.Bid1;
import com.catalin.javapersistence.models.test.Item1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DatabaseService {

        @PersistenceContext
        private EntityManager em;

//        private final GenericDao<Item1> itemDao;
        private final Item1Dao itemDao;

        public void init() {
                System.out.println("DatabaseService init");
                for (int i = 0; i < 10; i++) {
                        String itemName = "Item " + (i + 1);
                        Item1 item = new Item1();
                        item.setName(itemName);
                        Bid1 bid1 = new Bid1(new BigDecimal("1000.0"), item);
                        Bid1 bid2 = new Bid1(new BigDecimal("1100.0"), item);

                        itemDao.insert(item);
                        System.out.println("Inserted " + itemName + " into database");
                }
        }

        @Transactional
        public void clear() {
                em.createQuery("delete from Bid1 b").executeUpdate();
                em.createQuery("delete from Item1 i").executeUpdate();
        }
}

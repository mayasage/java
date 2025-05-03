package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.test.interceptor.AuditLogInterceptor;
import com.catalin.javapersistence.models.test.interceptor.AuditLogRecord;
import com.catalin.javapersistence.models.test.lifecycle.SingletonLog;
import com.catalin.javapersistence.models.test.lifecycle.ThreadLocalCurrentUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class FilterTest {

        @Autowired
        EntityManagerFactory emf;

        @Test
        public void detachMerge() {
                List<Long> ids = dummyData();
                Long item1Id = ids.getFirst();

                EntityManager em = emf.createEntityManager();

                em.getTransaction().begin();

                Item item = em.find(Item.class, item1Id);

                PersistenceUtil pu = Persistence.getPersistenceUtil();

                Assertions.assertAll(
                        () -> Assertions.assertFalse(pu.isLoaded(item, "bids"))
                );

                Assertions.assertEquals(3, item.getBids().size());

                Assertions.assertAll(
                        () -> Assertions.assertTrue(pu.isLoaded(item, "bids"))
                );

                em.detach(item);
                em.clear();

                Assertions.assertAll(
                        () -> Assertions.assertTrue(pu.isLoaded(item, "bids"))
                );

                item.setItemName("Hona lulu");

                User user = new User("ghanshyam");
                em.persist(user);

                Bid bid = new Bid();
                bid.setBidder(user);
                bid.setAmount(new BigDecimal(10));
                bid.setItem(item);
                item.addBid(bid);
                em.persist(bid);

                System.out.println("Watch update query on flush");

                Item mergedItem = em.merge(item);
                em.flush();

                em.clear();

                System.out.println("The SELECT query will become left join if MERGE ");

                Item itemX = em.find(Item.class, item1Id);
                Assertions.assertEquals(4, itemX.getBids().size());

                em.getTransaction().commit();
                em.close();

                SingletonLog log = SingletonLog.INSTANCE;
                System.out.println(log);
        }

        @Test
        public void logInterceptor() {
                dummyData();

                EntityManager em = emf.createEntityManager();

                em.getTransaction().begin();

                User john =
                        em.createQuery("select u from User u where username = 'john'", User.class).getSingleResult();

                Assertions.assertNotNull(john);

                em.getTransaction().commit();
                em.close();

                SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);

                Session session = sessionFactory.withOptions().interceptor(new AuditLogInterceptor()).openSession();
                AuditLogInterceptor auditLogInterceptor = (AuditLogInterceptor) ( (SessionImplementor) session).getInterceptor();
                auditLogInterceptor.setCurrentUserId(john.getId());
                auditLogInterceptor.setCurrentSession(session);

                session.beginTransaction();

                Item item1 = new Item("itemX");
                item1.setMetricWeight(new BigDecimal(2));
                item1.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                session.persist(item1);

                session.getTransaction().commit();
                session.close();

                EntityManager em2 = emf.createEntityManager();

                List<AuditLogRecord> auditLogRecords =
                        em2.createQuery("select a from AuditLogRecord a", AuditLogRecord.class).getResultList();

                Assertions.assertAll(
                        () -> Assertions.assertEquals(1, auditLogRecords.size())
                );

                em2.close();
        }

        @Test
        public void dynamicFilter() {
                dummyData();

                EntityManager em = emf.createEntityManager();

                Filter filter = em.unwrap(Session.class).enableFilter("limitByUserRankingDefault");
                filter.setParameter("currentUserRanking", 0);
                filter.validate();

                em.getTransaction().begin();

                List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();
                Assertions.assertAll(
                        () -> Assertions.assertEquals(0, items.size())
                );

                filter.setParameter("currentUserRanking", 100);
                List<Item> items1 = em.createQuery("select i from Item i", Item.class).getResultList();
                Assertions.assertAll(
                        () -> Assertions.assertEquals(1, items1.size())
                );

                filter.setParameter("currentUserRanking", 200);
                List<Item> items2 = em.createQuery("select i from Item i", Item.class).getResultList();
                Assertions.assertAll(
                        () -> Assertions.assertEquals(2, items2.size())
                );

                filter.setParameter("currentUserRanking", 300);
                List<Item> items3 = em.createQuery("select i from Item i", Item.class).getResultList();
                Assertions.assertAll(
                        () -> Assertions.assertEquals(3, items3.size())
                );

                em.getTransaction().commit();

                em.clear();

                em.getTransaction().begin();

                filter.setParameter("currentUserRanking", 100);

                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<Item> cq = cb.createQuery(Item.class);
                cq.select(cq.from(Item.class));
                List<Item> items4 = em.createQuery(cq).getResultList();

                Assertions.assertAll(
                        () -> Assertions.assertEquals(1, items4.size())
                );

                Item item = items4.getFirst();

                Assertions.assertAll(
                        () -> Assertions.assertEquals(3, item.getBids().size())
                );

                em.getTransaction().commit();

                em.close();
        }

        private List<Long> dummyData() {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                User john = new User("john");
                em.persist(john);
                ThreadLocalCurrentUser.INSTANCE.set(john);

                Item item1 = new Item("item1");
                item1.setMetricWeight(new BigDecimal(2));
                item1.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                em.persist(item1);

                Item item2 = new Item("item2");
                item2.setMetricWeight(new BigDecimal(3));
                item2.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                em.persist(item2);

                Item item3 = new Item("item3");
                item3.setMetricWeight(new BigDecimal(4));
                item3.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                em.persist(item3);

                User user1 = new User("ram");
                user1.setRanking(100);
                em.persist(user1);
                User user2 = new User("shyam");
                user2.setRanking(200);
                em.persist(user2);
                User user3 = new User("raghav");
                user3.setRanking(300);
                em.persist(user3);

                item1.setBuyer(user1);
                item1.setSeller(user1);
                item2.setBuyer(user2);
                item2.setSeller(user2);
                item3.setBuyer(user3);
                item3.setSeller(user3);

                Bid bid1 = new Bid();
                bid1.setBidder(user1);
                bid1.setAmount(new BigDecimal(10));
                bid1.setItem(item1);
                item1.addBid(bid1);
                em.persist(bid1);

                Bid bid2 = new Bid();
                bid2.setBidder(user1);
                bid2.setAmount(new BigDecimal(11));
                bid2.setItem(item1);
                item1.addBid(bid2);
                em.persist(bid2);

                Bid bid3 = new Bid();
                bid3.setBidder(user1);
                bid3.setAmount(new BigDecimal(12));
                bid3.setItem(item1);
                item1.addBid(bid3);
                em.persist(bid3);

                Bid bid4 = new Bid();
                bid4.setBidder(user1);
                bid4.setAmount(new BigDecimal(10));
                bid4.setItem(item2);
                item2.addBid(bid4);
                em.persist(bid4);

                Bid bid5 = new Bid();
                bid5.setBidder(user2);
                bid5.setAmount(new BigDecimal(11));
                bid5.setItem(item2);
                item2.addBid(bid5);
                em.persist(bid5);

                Bid bid6 = new Bid();
                bid6.setBidder(user3);
                bid6.setAmount(new BigDecimal(12));
                bid6.setItem(item2);
                item2.addBid(bid6);
                em.persist(bid6);

                Bid bid7 = new Bid();
                bid7.setBidder(user1);
                bid7.setAmount(new BigDecimal(10));
                bid7.setItem(item3);
                item3.addBid(bid7);
                em.persist(bid7);

                Bid bid8 = new Bid();
                bid8.setBidder(user2);
                bid8.setAmount(new BigDecimal(11));
                bid8.setItem(item3);
                item3.addBid(bid8);
                em.persist(bid8);

                Bid bid9 = new Bid();
                bid9.setBidder(user3);
                bid9.setAmount(new BigDecimal(12));
                bid9.setItem(item3);
                item3.addBid(bid9);
                em.persist(bid9);

                em.getTransaction().commit();
                em.close();

                return Arrays.asList(item1.getId(), bid1.getId());
        }
}

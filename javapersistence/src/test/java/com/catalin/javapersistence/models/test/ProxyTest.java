package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.repositories.test.BidRepository;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUtil;
import jakarta.persistence.Subgraph;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.collection.spi.PersistentMap;
import org.hibernate.proxy.HibernateProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ProxyTest {

        @Autowired
        private EntityManagerFactory emf;
        @Autowired
        private BidRepository bidRepository;

        @Test
        public void noDBQuery() {

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                I1 i1 = new I1();
                i1.setName("i1");
                em.persist(i1);
                em.getTransaction().commit();
                em.close();

                PersistenceUtil pu = Persistence.getPersistenceUtil();

                System.out.println("I have saved an I1.");

                System.out.println("The following should not fire any query.");
                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();
                I1 i1Proxy = em2.getReference(I1.class, i1.getId());
                em2.getTransaction().commit();
                em2.close();

                Assertions.assertAll(
                        () -> Assertions.assertFalse(pu.isLoaded(i1Proxy)),
                        () -> Assertions.assertFalse(Hibernate.isInitialized(i1Proxy))
                );

                Assertions.assertThrows(LazyInitializationException.class, () -> System.out.println(i1Proxy));

                EntityManager em3 = emf.createEntityManager();
                em3.getTransaction().begin();

                Assertions.assertInstanceOf(HibernateProxy.class, i1Proxy);

                em3.merge(i1Proxy);
                Assertions.assertThrows(LazyInitializationException.class, () -> System.out.println(i1Proxy));

                I1 mergedProxy = em3.merge(i1Proxy);
                System.out.println("getId does not lead to a DB query.");
                System.out.println(mergedProxy.getId());

                Assertions.assertAll(
                        () -> Assertions.assertInstanceOf(HibernateProxy.class, mergedProxy),
                        () -> Assertions.assertFalse(pu.isLoaded(i1Proxy)),
                        () -> Assertions.assertFalse(Hibernate.isInitialized(i1Proxy)),
                        () -> Assertions.assertFalse(pu.isLoaded(mergedProxy)),
                        () -> Assertions.assertFalse(Hibernate.isInitialized(mergedProxy))
                );

                System.out.println("The following will trigger a query regardless of whether toString is accessing " +
                                   "fields or not.");
                System.out.println(mergedProxy);
                em3.getTransaction().commit();
                em3.close();

                Assertions.assertAll(
                        () -> Assertions.assertInstanceOf(HibernateProxy.class, mergedProxy),
                        () -> Assertions.assertFalse(pu.isLoaded(i1Proxy)),
                        () -> Assertions.assertFalse(Hibernate.isInitialized(i1Proxy)),
                        () -> Assertions.assertTrue(pu.isLoaded(mergedProxy)),
                        () -> Assertions.assertTrue(Hibernate.isInitialized(mergedProxy))
                );

                EntityManager em4 = emf.createEntityManager();
                em4.getTransaction().begin();
                System.out.println("getName() called.");
                String name = mergedProxy.getName();
                Assertions.assertInstanceOf(HibernateProxy.class, mergedProxy);
                em4.getTransaction().commit();
                em4.close();

                Assertions.assertThrows(LazyInitializationException.class, () -> Hibernate.initialize(i1Proxy));

                EntityManager em5 = emf.createEntityManager();
                em5.getTransaction().begin();
                Assertions.assertThrows(LazyInitializationException.class, () -> Hibernate.initialize(i1Proxy));
                I1 mergedI1 = em5.merge(i1Proxy);
                Hibernate.initialize(mergedI1);
                Assertions.assertAll(
                        () -> Assertions.assertThrows(LazyInitializationException.class,
                                () -> Hibernate.initialize(i1Proxy)),
                        () -> Assertions.assertFalse(pu.isLoaded(i1Proxy)),
                        () -> Assertions.assertFalse(Hibernate.isInitialized(i1Proxy)),
                        () -> Assertions.assertTrue(pu.isLoaded(mergedI1)),
                        () -> Assertions.assertTrue(Hibernate.isInitialized(mergedI1))
                );
                em5.getTransaction().commit();
                em5.close();
        }

        @Test
        public void user() {
                EntityManager em = emf.createEntityManager();

                em.getTransaction().begin();

                Item item = new Item("Item");
                item.setMetricWeight(new BigDecimal(2));
                item.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                em.persist(item);

                User john = new User("John Smith");
                em.persist(john);

                item.setBuyer(john);
                em.getTransaction().commit();

                em.close();

                PersistenceUtil pu = Persistence.getPersistenceUtil();

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                Item item1 = em2.find(Item.class, item.getId());

                Assertions.assertAll(
                        () -> Assertions.assertTrue(pu.isLoaded(item1)),
                        () -> Assertions.assertFalse(pu.isLoaded(item1, "buyer"))
                );

                User buyer = item1.getBuyer();
                Assertions.assertFalse(pu.isLoaded(item1, "buyer"));

                Long buyerId = buyer.getId();
                Assertions.assertFalse(pu.isLoaded(item1, "buyer"));

                String username = buyer.getUsername();
                Assertions.assertTrue(pu.isLoaded(item1, "buyer"));

                em2.getTransaction().commit();
                em2.close();
        }

        @Test
        public void noSelectFired() {
                EntityManager em = emf.createEntityManager();

                em.getTransaction().begin();

                Item item = new Item("Item");
                item.setMetricWeight(new BigDecimal(2));
                item.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                em.persist(item);

                User john = new User("John Smith");
                em.persist(john);
                em.getTransaction().commit();

                em.close();

                System.out.println("You should see no SELECT statement from now on.");

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();
                Item item1 = em2.getReference(Item.class, item.getId());
                User user = em2.getReference(User.class, john.getId());
                Bid bid = new Bid();
                bid.setBidder(user);
                bid.setAmount(new BigDecimal(2));
                bid.setItem(item1);
                em2.persist(bid);
                em2.getTransaction().commit();
                em2.close();
        }

        @Test
        public void lazyCollections() {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                Item item = new Item("Item");
                item.setMetricWeight(new BigDecimal(2));
                item.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                em.persist(item);

                User user = new User("John Smith");
                em.persist(user);

                Bid bid = new Bid();
                bid.setBidder(user);
                bid.setAmount(new BigDecimal(2));
                bid.setItem(item);
                item.addBid(bid);
                em.persist(bid);

                Bid bid1 = new Bid();
                bid1.setBidder(user);
                bid1.setAmount(new BigDecimal(3));
                bid1.setItem(item);
                item.addBid(bid1);
                em.persist(bid1);

                Bid bid2 = new Bid();
                bid2.setBidder(user);
                bid2.setAmount(new BigDecimal(4));
                bid2.setItem(item);
                item.addBid(bid2);
                em.persist(bid2);

                em.getTransaction().commit();
                em.close();

                PersistenceUtil pu = Persistence.getPersistenceUtil();

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                Item item1 = em2.getReference(Item.class, item.getId());

                Assertions.assertFalse(pu.isLoaded(item1));

                Map<Long, Bid> bids = item1.getBids();

                System.out.println("bids class: " + bids.getClass());

                Assertions.assertAll(
                        () -> Assertions.assertTrue(pu.isLoaded(item1)),
                        () -> Assertions.assertFalse(pu.isLoaded(item1, "bids")),
                        () -> Assertions.assertFalse(bids instanceof HashMap),
                        () -> Assertions.assertInstanceOf(PersistentMap.class, bids),
                        () -> Assertions.assertFalse(pu.isLoaded(bids))
                );

                System.out.println("Loading 1 bid. You should see SELECT statements");

                for (Map.Entry<Long, Bid> bidEntry : bids.entrySet()) {
                        System.out.println("Got a bidEntry");
                        break;
                }
//                Hibernate.initialize(bids);
                Assertions.assertEquals(3, bids.size());

                Assertions.assertAll(
                        () -> Assertions.assertTrue(pu.isLoaded(item1)),
                        () -> Assertions.assertTrue(pu.isLoaded(item1, "bids")),
                        () -> Assertions.assertTrue(pu.isLoaded(bids))
                );

                em2.getTransaction().commit();
                em2.close();
        }

        @Test
        public void eagerJoin() {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                Item item = new Item("Item");
                item.setMetricWeight(new BigDecimal(2));
                item.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                em.persist(item);

                User user = new User("John Smith");
                em.persist(user);

                Bid bid = new Bid();
                bid.setBidder(user);
                bid.setAmount(new BigDecimal(2));
                bid.setItem(item);
                item.addBid(bid);
                em.persist(bid);

                Bid bid1 = new Bid();
                bid1.setBidder(user);
                bid1.setAmount(new BigDecimal(3));
                bid1.setItem(item);
                item.addBid(bid1);
                em.persist(bid1);

                Bid bid2 = new Bid();
                bid2.setBidder(user);
                bid2.setAmount(new BigDecimal(4));
                bid2.setItem(item);
                item.addBid(bid2);
                em.persist(bid2);

                em.getTransaction().commit();
                em.close();

                PersistenceUtil pu = Persistence.getPersistenceUtil();

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                Item item1 = em2.find(Item.class, item.getId());

                Assertions.assertAll(
                        () -> Assertions.assertTrue(pu.isLoaded(item1)),
                        () -> Assertions.assertTrue(pu.isLoaded(item1, "bids")),
                        () -> Assertions.assertTrue(pu.isLoaded(item1.getBids())),
                        () -> Assertions.assertEquals(3, item1.getBids().size())
                );

                em2.getTransaction().commit();
                em2.close();

                Map<Long, Bid> bids = item1.getBids();
                for (Map.Entry<Long, Bid> bidEntry : bids.entrySet()) {
                        System.out.println("Got a bidEntry");
                }
        }

        @Test
        public void nPlusOneSelectProblem() {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

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
                em.persist(user1);
                User user2 = new User("shyam");
                em.persist(user2);
                User user3 = new User("raghav");
                em.persist(user3);

                item1.setBuyer(user1);
                item2.setBuyer(user2);
                item3.setBuyer(user3);

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

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                List<Item> items =
                        em2.createQuery("select i from Item i where i.id IN :ids", Item.class).setParameter("ids",
                                new ArrayList<>(Arrays.asList(item1.getId(), item2.getId(), item3.getId()))).getResultList();

                System.out.println("Now you'll see 3 select statements");

                for (Item itemX : items) {
                        System.out.println("Got a bidEntry: " + itemX.getBuyer().getUsername());
                }

                System.out.println("How many queries?");
                System.out.println("You'll see 3 selects again");

                for (Item itemX : items) {
                        System.out.println("Got a bidEntry: " + itemX.getBids());
                }

                em2.getTransaction().commit();
                em2.close();
        }

        @Test
        public void cartesianProductProblem() {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                Item item = new Item("Item");
                item.setMetricWeight(new BigDecimal(2));
                item.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                em.persist(item);

                User user = new User("John Smith");
                em.persist(user);

                Bid bid = new Bid();
                bid.setBidder(user);
                bid.setAmount(new BigDecimal(2));
                bid.setItem(item);
                item.addBid(bid);
                em.persist(bid);

                Bid bid1 = new Bid();
                bid1.setBidder(user);
                bid1.setAmount(new BigDecimal(3));
                bid1.setItem(item);
                item.addBid(bid1);
                em.persist(bid1);

                Bid bid2 = new Bid();
                bid2.setBidder(user);
                bid2.setAmount(new BigDecimal(4));
                bid2.setItem(item);
                item.addBid(bid2);
                em.persist(bid2);

                item.addImage(new Filename("Background"), new Image("background.jpg", 640, 480));
                item.addImage(new Filename("Foreground"), new Image("foreground.jpg", 800, 600));
                item.addImage(new Filename("Landscape"), new Image("landscape.jpg", 1024, 768));

                em.getTransaction().commit();
                em.close();

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                System.out.println("This query will fetch 9 rows (3 images * 3 bids).");

                Item item1 = em2.find(Item.class, item.getId());

                PersistenceUtil pu = Persistence.getPersistenceUtil();

                Assertions.assertAll(
                        () -> Assertions.assertTrue(pu.isLoaded(item1)),
                        () -> Assertions.assertTrue(pu.isLoaded(item1.getBids())),
                        () -> Assertions.assertTrue(pu.isLoaded(item1.getImages()))
                );

                Assertions.assertAll(
                        () -> Assertions.assertEquals(3, item1.getBids().size()),
                        () -> Assertions.assertEquals(3, item1.getImages().size())
                );

                em2.getTransaction().commit();
                em2.close();
        }

        @Test
        public void batchFetching() {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                Item item = new Item("Item");
                item.setMetricWeight(new BigDecimal(2));
                item.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                em.persist(item);

                User user = new User("John Smith");
                em.persist(user);

                Bid bid = new Bid();
                bid.setBidder(user);
                bid.setAmount(new BigDecimal(2));
                bid.setItem(item);
                item.addBid(bid);
                em.persist(bid);

                Bid bid1 = new Bid();
                bid1.setBidder(user);
                bid1.setAmount(new BigDecimal(3));
                bid1.setItem(item);
                item.addBid(bid1);
                em.persist(bid1);

                Bid bid2 = new Bid();
                bid2.setBidder(user);
                bid2.setAmount(new BigDecimal(4));
                bid2.setItem(item);
                item.addBid(bid2);
                em.persist(bid2);

                item.addImage(new Filename("Background"), new Image("background.jpg", 640, 480));
                item.addImage(new Filename("Foreground"), new Image("foreground.jpg", 800, 600));
                item.addImage(new Filename("Landscape"), new Image("landscape.jpg", 1024, 768));

                em.getTransaction().commit();
                em.close();

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                Item item1 = em2.createQuery("select i from Item i", Item.class).getSingleResult();
                Assertions.assertNotNull(item1);

                PersistenceUtil pu = Persistence.getPersistenceUtil();

                Assertions.assertAll(
                        () -> Assertions.assertTrue(pu.isLoaded(item1)),
                        () -> Assertions.assertFalse(pu.isLoaded(item1, "bids")),
                        () -> Assertions.assertFalse(pu.isLoaded(item1, "images")),
                        () -> Assertions.assertFalse(pu.isLoaded(item1.getBids())),
                        () -> Assertions.assertFalse(pu.isLoaded(item1.getImages()))
                );

                System.out.println("I have 3 images and 3 bids. You'll see only 2 select queries.");

                Map<Long, Bid> bids = item1.getBids();
                for (Map.Entry<Long, Bid> bidEntry : bids.entrySet()) {
                        System.out.println("Got a bidEntry");
                }

                em2.getTransaction().commit();
                em2.close();
        }

        @Test
        public void dynamicEagerFetching() {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

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
                em.persist(user1);
                User user2 = new User("shyam");
                em.persist(user2);
                User user3 = new User("raghav");
                em.persist(user3);

                item1.setBuyer(user1);
                item2.setBuyer(user2);
                item3.setBuyer(user3);

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

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                List<Item> items =
                        em2.createQuery("select i from Item i join fetch i.bids", Item.class).getResultList();

                System.out.println("How many queries?");
                System.out.println("You'll see 3 selects again");

                for (Item itemX : items) {
                        System.out.println("Got a bidEntry: " + itemX.getBids());
                }

                em2.getTransaction().commit();
                em2.close();

                EntityManager em3 = emf.createEntityManager();
                em3.getTransaction().begin();

                System.out.println("Criteria Query");

                CriteriaBuilder cb = em3.getCriteriaBuilder();
                CriteriaQuery<Item> cq = cb.createQuery(Item.class);
                Root<Item> root = cq.from(Item.class);
                root.fetch("bids", JoinType.INNER);
                cq.select(root);
                List<Item> items1 = em3.createQuery(cq).getResultList();
                em3.getTransaction().commit();
                em3.close();

                for (Item itemX : items1) {
                        System.out.println("Got a bidEntry: " + itemX.getBids());

                        Assertions.assertAll(
                                () -> Assertions.assertDoesNotThrow(() -> itemX.getBuyer()),

                                () -> Assertions.assertThrows(LazyInitializationException.class,
                                        () -> itemX.getBuyer().getUsername())
                        );
                }
        }

        @Test
        public void fetchProfile() {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

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
                em.persist(user1);
                User user2 = new User("shyam");
                em.persist(user2);
                User user3 = new User("raghav");
                em.persist(user3);

                item1.setBuyer(user1);
                item2.setBuyer(user2);
                item3.setBuyer(user3);

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

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                List<Item> items = em2.createQuery("select i from Item i", Item.class).getResultList();

                System.out.println("You'll see 3 selects");

                for (Item itemX : items) {
                        System.out.println("Got a bidEntry: " + itemX.getBids());
                }

                System.out.println("You'll see 3 selects again");

                for (Item itemX : items) {
                        System.out.println("Got a bidEntry: " + itemX.getBuyer().getUsername());
                }

                em2.getTransaction().commit();
                em2.close();

                EntityManager em3 = emf.createEntityManager();
                em3.getTransaction().begin();

                em3.unwrap(Session.class).enableFetchProfile("joinItemBids");

                List<Item> items1 =
                        em3
                                .createQuery("select i from Item i", Item.class)
                                .getResultList();

                Assertions.assertEquals(3, items1.size());

                System.out.println("You'll not see any query.");

                for (Item itemX : items1) {
                        System.out.println("Got a bidEntry: " + itemX.getBids());
                }

                em3.unwrap(Session.class).enableFetchProfile("joinItemBuyer");

                System.out.println("You'll see 1 query.");

                List<Item> items2 =
                        em3
                                .createQuery("select i from Item i", Item.class)
                                .getResultList();

                System.out.println("No more queries");

                for (Item itemX : items2) {
                        System.out.println("Got a bidEntry: " + itemX.getBuyer().getUsername());
                }

                em3.getTransaction().commit();
                em3.close();
        }

        @Test
        public void fetchEntityGraph() {
                List<Long> ids = dummyData();
                Long itemId = ids.getFirst();

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                EntityGraph<Item> eg = em2.createEntityGraph(Item.class);
                eg.addAttributeNodes(Item_.buyer.getName());

                Map<String, Object> props = new HashMap<>();
                props.put(
                        "javax.persistence.loadgraph",
//                        em2.getEntityGraph(Item.class.getSimpleName())
//                        em2.getEntityGraph("item-buyer")
                        eg
                );

//                Item item = em2.find(Item.class, item1.getId(), props);
//                Item item = em2.find(Item.class, item1.getId());
                Item item =
                        em2
                                .createQuery("select i from Item i where i.id = :id", Item.class)
                                .setParameter("id", itemId)
                                .setHint("javax.persistence.loadgraph", eg)
                                .getSingleResult();

                PersistenceUtil pu = Persistence.getPersistenceUtil();

                Assertions.assertAll(
                        () -> Assertions.assertTrue(() -> pu.isLoaded(item)),
                        () -> Assertions.assertFalse(() -> pu.isLoaded(item, "bids")),
//                        () -> Assertions.assertFalse(() -> pu.isLoaded(item, "buyer"))
                        () -> Assertions.assertTrue(() -> pu.isLoaded(item, "buyer"))
                );

                em2.getTransaction().commit();
                em2.close();
        }

        @Test
        public void entityGraphWithSubgraph() {
                List<Long> ids = dummyData();
                Long itemId = ids.getFirst();
                Long bidId = ids.get(1);

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                EntityGraph<Bid> bidEntityGraph = em2.createEntityGraph(Bid.class);
//                bidEntityGraph.addAttributeNodes(Bid_.bidder.getName());
//                bidEntityGraph.addAttributeNodes(Bid_.item.getName());
                bidEntityGraph.addAttributeNodes(Bid_.bidder.getName(), Bid_.item.getName());
                Subgraph<Item> itemSubgraph = bidEntityGraph.addSubgraph(Bid_.item.getName());
                itemSubgraph.addAttributeNodes(Item_.bids.getName());
                Map<String, Object> props = new HashMap<>();
//                props.put("javax.persistence.loadgraph", bidEntityGraph);
                props.put("javax.persistence.loadgraph", em2.getEntityGraph("graph.BidItemBuyerBids"));

                PersistenceUtil pu = Persistence.getPersistenceUtil();

                Bid bid = em2.find(Bid.class, bidId, props);

                Assertions.assertAll(
                        () -> Assertions.assertTrue(pu.isLoaded(bid)),
                        () -> Assertions.assertTrue(pu.isLoaded(bid, "bidder")),
                        () -> Assertions.assertTrue(pu.isLoaded(bid, "item")),
                        () -> Assertions.assertTrue(pu.isLoaded(bid.getItem(), "buyer")),
                        () -> Assertions.assertTrue(pu.isLoaded(bid.getItem(), "bids"))
                );

                System.out.println("The following shouldn't trigger queries.");
                bid.getItem().getBuyer().getUsername();
                System.out.println("bids: " + bid.getItem().getBids());

                em2.getTransaction().commit();
                em2.close();
        }

        @Test
        public void fetchGraph() {
                List<Long> ids = dummyData();
                Long bidId = ids.get(1);

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                EntityGraph<Bid> bidEntityGraph = em2.createEntityGraph(Bid.class);
                bidEntityGraph.addAttributeNodes(Bid_.amount.getName());
                Map<String, Object> props = new HashMap<>();
                props.put("javax.persistence.fetchgraph", bidEntityGraph);

                System.out.println("I have made bidder eager, but the following will not fetch it.");
                Bid bid = em2.find(Bid.class, bidId, props);

                PersistenceUtil pu = Persistence.getPersistenceUtil();

                Assertions.assertAll(
                        () -> Assertions.assertFalse(pu.isLoaded(bid, "bidder"))
                );

                em2.getTransaction().commit();
                em2.close();
        }

        private List<Long> dummyData() {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

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
                em.persist(user1);
                User user2 = new User("shyam");
                em.persist(user2);
                User user3 = new User("raghav");
                em.persist(user3);

                item1.setBuyer(user1);
                item2.setBuyer(user2);
                item3.setBuyer(user3);

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

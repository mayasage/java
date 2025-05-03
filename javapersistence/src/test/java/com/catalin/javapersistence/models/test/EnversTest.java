package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.test.lifecycle.ThreadLocalCurrentUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.criteria.MatchMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class EnversTest {

        @Autowired
        private EntityManagerFactory emf;

        @Test
        public void test() {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                User john = new User("john");
                em.persist(john);

                User cena = new User("cena");
                em.persist(cena);

                em.getTransaction().commit();
                em.close();

                Date johnCreatedBefore = new Date();

                EntityManager em1 = emf.createEntityManager();
                em1.getTransaction().begin();

                User ap = new User("ap");
                em1.persist(ap);

                User dhillon = new User("dhillon");
                em1.persist(dhillon);

                em1.getTransaction().commit();
                em1.close();

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                john.setUsername("nari");
                ap.setUsername("lucid");

                User nari = em2.merge(john);
                User lucid = em2.merge(ap);
                em2.persist(nari);
                em2.persist(lucid);

//                em2.persist(john);
//                em2.persist(ap);

                em2.getTransaction().commit();
                em2.close();

                Date johnUpdatedBefore = new Date();

                EntityManager em3 = emf.createEntityManager();
                em3.getTransaction().begin();
                User nari1 = em3.merge(nari);
                em3.remove(nari1);
                em3.getTransaction().commit();
                em3.close();

                Date nariDeletedBefore = new Date();

                EntityManager em4 = emf.createEntityManager();
                AuditReader ar = AuditReaderFactory.get(em4);

                List <Number> userRevisions = ar.getRevisions(User.class, nari.getId());
                Assertions.assertEquals(3, userRevisions.size());

                for (Number revision : userRevisions) {
                        Date revisionDate = ar.getRevisionDate(revision);
                        Number refectNumber = ar.getRevisionNumberForDate(revisionDate);
                        Assertions.assertEquals(revision, refectNumber);
                }

//                AuditQuery query = ar.createQuery().forRevisionsOfEntity(User.class, false, true);
//                List<Object[]> result = query.getResultList();
//
//                for (Object[] tuple : result) {
//
//                        User user = (User) tuple[0];
//                        DefaultRevisionEntity defaultRevisionEntity = (DefaultRevisionEntity) tuple[1];
//                        RevisionType revisionType = (RevisionType) tuple[2];
//
//                        System.out.println("Tuple: " + user + ", defaultRevisionEntity: " + defaultRevisionEntity + ", revisionType: " + revisionType);
//                }

                User john1 = ar.find(User.class, john.getId(), johnCreatedBefore);
                User nari2 = ar.find(User.class, john.getId(), johnUpdatedBefore);
                User noUsername = ar.find(User.class, john.getId(), nariDeletedBefore);

                Assertions.assertAll(
                        () -> Assertions.assertEquals("john", john1.getUsername()),
                        () -> Assertions.assertEquals("nari", nari2.getUsername()),
                        () -> Assertions.assertNull(noUsername)
//                        () -> Assertions.assertEquals(null, noUsername.getUsername())
                );

                Number johnUpdatedBeforeRevisionNumber = ar.getRevisionNumberForDate(johnUpdatedBefore);

                AuditQuery query = ar.createQuery().forEntitiesAtRevision(User.class, johnUpdatedBeforeRevisionNumber);
                query.add(AuditEntity.property("username").like("na", MatchMode.START));
                query.addProjection(AuditEntity.property("username"));
                query.setFirstResult(0);
                query.setMaxResults(10);

                Assertions.assertEquals(1, query.getResultList().size());
                String username = (String) query.getResultList().getFirst();
                Assertions.assertEquals("nari", username);

                em4.getTransaction().begin();
                User nariAfterDeletion = em4.find(User.class, nari2.getId());
                Assertions.assertNull(nariAfterDeletion);
                em4.unwrap(Session.class).replicate(nari2, ReplicationMode.OVERWRITE);
                em4.flush();
                em4.clear();
                User nariReversal = em4.find(User.class, nari2.getId());
                Assertions.assertNotNull(nariReversal);
                Assertions.assertEquals("nari", nariReversal.getUsername());
                em4.getTransaction().commit();
                em4.close();
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

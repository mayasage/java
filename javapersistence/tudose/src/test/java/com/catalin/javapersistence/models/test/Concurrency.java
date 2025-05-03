package com.catalin.javapersistence.models.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PessimisticLockException;
import jakarta.persistence.RollbackException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

@SpringBootTest
public class Concurrency {

        @Autowired
        EntityManagerFactory emf;

        @Test
        public void firstCommitWins() throws ExecutionException, InterruptedException {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                A1 a = new A1();
                a.setName("a");
                a.setDescription("a");
                em.persist(a);
                em.getTransaction().commit();
                em.close();

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();
                A1 a2 = em2.find(A1.class, a.getId());
                a2.setName("b");

                Executors.newSingleThreadExecutor().submit(() -> {
                        EntityManager em3 = emf.createEntityManager();
                        em3.getTransaction().begin();
                        A1 a3 = em3.find(A1.class, a.getId());
                        a3.setName("c");
                        a3.setDescription("c");
                        em3.getTransaction().commit();
                        em3.close();
                }).get();

                Assertions.assertThrows(OptimisticLockException.class, em2::flush);
                em2.close();
        }

        @Test
        public void queryLock() throws ExecutionException, InterruptedException {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                C1[] c1s = new C1[3];
                for (int i = 0; i < 3; i++) {
                        C1 c1 = new C1();
                        c1.setName("c" + i);
                        em.persist(c1);
                        c1s[i] = c1;
                }
                I1[] i1s = new I1[2];
                for (int i = 0; i < 2; i++) {
                        I1 i1 = new I1();
                        i1.setName("i" + i);
                        i1.setBuyNowPrice(new BigDecimal(i + 1));
                        em.persist(i1);
                        i1s[i] = i1;
                }
                i1s[0].setCategory(c1s[0]);
                i1s[1].setCategory(c1s[1]);
                em.getTransaction().commit();
                em.close();

                BigDecimal total = BigDecimal.ZERO;
                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();
                for (int i = 0; i < c1s.length; i++) {
                        C1 c1 = c1s[i];
                        System.out.println("i: " + i + ", c1: " + c1);

                        Assertions.assertFalse(em2.contains(c1));

                        List<I1> is =
                                em2.createQuery(
                                                "select i from I1 i where i.category.id = :cid",
                                                I1.class
                                        )
                                        .setLockMode(LockModeType.OPTIMISTIC)
                                        .setParameter("cid", c1.getId())
                                        .getResultList();

                        System.out.println("i: " + i + ", is: " + is);

                        BigDecimal sum = BigDecimal.ZERO;
                        for (int j = 0; j < is.size(); j++) {
                                I1 i1 = is.get(j);

                                System.out.println("j: " + j + ", i1: " + i1);

                                sum = sum.add(i1.getBuyNowPrice());
                                total = total.add(i1.getBuyNowPrice());
                        }
                        System.out.println("c1: " + c1 + ", sum: " + sum);

                        if (i == 0) {
                                System.out.println("first category");
                                Executors.newSingleThreadExecutor().submit(() -> {
                                        EntityManager em3 = emf.createEntityManager();
                                        em3.getTransaction().begin();
                                        List<I1> is2 =
                                                em3.createQuery(
                                                                "select i from I1 i where i.category.id = :cid",
                                                                I1.class
                                                        )
                                                        .setParameter("cid", c1.getId())
                                                        .getResultList();
                                        System.out.println("will fire update query");
                                        is2.getFirst().setCategory(c1s[2]);
                                        is2.getFirst().setName("aaaa");
                                        em3.getTransaction().commit();
                                        em3.close();
                                }).get();
                        }
                }
                Assertions.assertThrows(RollbackException.class, () -> em2.getTransaction().commit());
                em2.close();
        }

        @Test
        public void optimisticLock() throws ExecutionException, InterruptedException {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                A1 a1 = new A1();
                a1.setName("a");
                em.persist(a1);
                em.getTransaction().commit();
                em.close();

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();
//                A1 x = em2.find(A1.class, a1.getId(), LockModeType.OPTIMISTIC);
                A1 x = em2.find(A1.class, a1.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                Assertions.assertEquals("a", x.getName());
                x.setName("b");

                Executors.newSingleThreadExecutor().submit(() -> {
                        EntityManager em3 = emf.createEntityManager();
                        em3.getTransaction().begin();
                        A1 x2 = em3.find(A1.class, a1.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                        x2.setName("c");
                        em3.getTransaction().commit();
                        em3.close();
                }).get();

//                Assertions.assertEquals("a", x.getName());
//                A1 x3 = em2.find(A1.class, a1.getId());
//                Assertions.assertEquals("a", x3.getName());
//
//                A1 x4 = em2.createQuery("select a from A1 a", A1.class).getSingleResult();
//                Assertions.assertEquals("a", x4.getName());
                Assertions.assertThrows(RollbackException.class,() -> em2.getTransaction().commit());
                em2.close();

                EntityManager em3 = emf.createEntityManager();
                em3.getTransaction().begin();
                A1 x2 = em3.find(A1.class, a1.getId());
                Assertions.assertEquals("c", x2.getName());
                em3.getTransaction().commit();
                em3.close();
        }

        @Test
        public void versionIncrementOnRead() {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                A1 a1 = new A1();
                a1.setName("a");
                em.persist(a1);
                em.getTransaction().commit();
                em.close();

                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();
                A1 x = em2.find(A1.class, a1.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                A1 x2 = em2.find(A1.class, a1.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                A1 x3 = em2.find(A1.class, a1.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                System.out.println("did the update happen before or after this line?");
                em2.getTransaction().commit();
                em2.close();
        }

        @Test
        public void pessimisticLock() throws ExecutionException, InterruptedException {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                C1[] c1s = new C1[3];
                for (int i = 0; i < 3; i++) {
                        C1 c1 = new C1();
                        c1.setName("c" + i);
                        em.persist(c1);
                        c1s[i] = c1;
                }
                I1[] i1s = new I1[2];
                for (int i = 0; i < 2; i++) {
                        I1 i1 = new I1();
                        i1.setName("i" + i);
                        i1.setBuyNowPrice(new BigDecimal(i + 1));
                        em.persist(i1);
                        i1s[i] = i1;
                }
                i1s[0].setCategory(c1s[0]);
                i1s[1].setCategory(c1s[1]);
                em.getTransaction().commit();
                em.close();

                BigDecimal total = BigDecimal.ZERO;
//                Map<String,Object> properties = new HashMap<>();
//                properties.put("javax.persistence.query.timeout", 1000);
//                properties.put("javax.persistence.lock.timeout", 1000);
//                EntityManager em2 = emf.createEntityManager(properties);
//                em2.setProperty("javax.persistence.query.timeout", 1000);
//                em2.setProperty("javax.persistence.lock.timeout", 1000);
                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();
                for (int i = 0; i < c1s.length; i++) {
                        C1 c1 = c1s[i];
                        System.out.println("i: " + i + ", c1: " + c1);

                        Assertions.assertFalse(em2.contains(c1));

                        List<I1> is =
                                em2.createQuery(
                                                "select i from I1 i where i.category.id = :cid",
                                                I1.class
                                        )
//                                        .setHint("javax.persistence.lock.timeout", "50")
//                                        .setHint("javax.persistence.query.timeout", "50")
//                                        .setHint("javax.persistence.lock.timeout", 50)
//                                        .setHint("javax.persistence.query.timeout", 50)
//                                        .setLockMode(LockModeType.PESSIMISTIC_READ)
                                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                                        .setParameter("cid", c1.getId())
                                        .getResultList();

                        System.out.println("i: " + i + ", is: " + is);

                        BigDecimal sum = BigDecimal.ZERO;
                        for (int j = 0; j < is.size(); j++) {
                                I1 i1 = is.get(j);

                                System.out.println("j: " + j + ", i1: " + i1);

                                sum = sum.add(i1.getBuyNowPrice());
                                total = total.add(i1.getBuyNowPrice());
                        }
                        System.out.println("c1: " + c1 + ", sum: " + sum);

                        if (i == 0) {
                                System.out.println("first category");
                                Executors.newSingleThreadExecutor().submit(() -> {
                                        try {
                                                EntityManager em3 = emf.createEntityManager();
                                                em3.getTransaction().begin();
                                                List<I1> is2 =
                                                        em3.createQuery(
                                                                        "select i from I1 i where i.category.id = :cid",
                                                                        I1.class
                                                                )
                                                              .setLockMode(LockModeType.PESSIMISTIC_WRITE)
//                                                                .setLockMode(LockModeType.PESSIMISTIC_READ)
                                                                .setParameter("cid", c1.getId())
                                                                .getResultList();
                                                System.out.println("will fire update query");
                                                is2.getFirst().setCategory(c1s[2]);
                                                is2.getFirst().setName("easily see change");
//                                              em3.getTransaction().commit();
                                                Assertions.assertThrows(RollbackException.class, () -> em3.getTransaction().commit());
                                                System.out.println("em3 after commit");
                                                em3.close();
                                        } catch (Exception ex) {
                                                Assertions.assertEquals(PessimisticLockException.class, ex.getClass());
                                        }
                                }).get();
                        }
                }
                System.out.println("em2 before commit");
                em2.getTransaction().commit();
                System.out.println("em2 after commit");
                em2.close();

                System.out.println("total: " + total);
        }
}

package com.catalin.javapersistence.models.test;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TransactionRequiredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class NonTransactionalTest {

        @Autowired
        private EntityManagerFactory emf;

        @Test
        public void autocommit() {
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                I1 i1 = new I1();
                i1.setName("item1");
                i1.setBuyNowPrice(new BigDecimal(1));
                em.persist(i1);
                em.getTransaction().commit();
                em.close();

                EntityManager em2 = emf.createEntityManager();
                I1 x = em2.find(I1.class, i1.getId());
                x.setName("itemX");
                Assertions.assertEquals(
                        "item1",
                        em2
                                .createQuery(
                                        "SELECT i.name FROM I1 i WHERE id = :id",
                                        String.class
                                )
                                .setParameter("id", i1.getId())
                                .getSingleResult()
                );
                Assertions.assertEquals(
                        "itemX",
                        em2
                                .createQuery(
                                        "SELECT i FROM I1 i WHERE id = :id",
                                        I1.class
                                )
                                .setParameter("id", i1.getId())
                                .getSingleResult()
                                .getName()
                );
                Assertions.assertThrows(TransactionRequiredException.class, em2::flush);
                em2.refresh(x);
                Assertions.assertEquals("item1", x.getName());
                em2.close();
        }

        @Test
        public void queueing() {
                EntityManager em = emf.createEntityManager();
                I1 i1 = new I1();
                i1.setName("item1");
                i1.setBuyNowPrice(new BigDecimal(1));
                em.persist(i1);
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                EntityManager em2 = emf.createEntityManager();
                I1 x = em2.find(I1.class, i1.getId());
                Assertions.assertEquals(i1.getName(), x.getName());
                em2.close();

                EntityManager em3 = emf.createEntityManager();
                I1 x2 = em3.merge(x);
                x2.setName("itemX");
                em3.getTransaction().begin();
                em3.persist(x2);
                em3.getTransaction().commit();
                em3.close();

                EntityManager em4 = emf.createEntityManager();
                em4.getTransaction().begin();
                I1 x3 = em4.find(I1.class, i1.getId());
                Assertions.assertEquals(x2.getName(), x3.getName());
                em4.getTransaction().commit();
                em4.close();
        }
}

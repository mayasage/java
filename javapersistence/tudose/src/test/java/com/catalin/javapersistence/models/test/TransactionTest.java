package com.catalin.javapersistence.models.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.TransactionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@SpringBootTest
public class TransactionTest {

        @Autowired
        JpaTransactionManager ptm;
//        PlatformTransactionManager ptm;

        @Autowired
        EntityManagerFactory emf;

        @Test
        public void transactionTemplate() {

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                I1 i1 = new I1();
                i1.setName("item1");
                i1.setBuyNowPrice(new BigDecimal(1));
                em.persist(i1);
                em.getTransaction().commit();
                em.close();

                EntityManager em2 = emf.createEntityManager();
                TransactionTemplate tt = new TransactionTemplate();
                tt.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
                tt.setTransactionManager(ptm);
                tt.setReadOnly(true);
                tt.execute((status) -> {
                        I1 x = em2.find(I1.class, i1.getId());
                        Assertions.assertEquals("item1", x.getName());
                        return null;
                });
                em2.close();
        }
}

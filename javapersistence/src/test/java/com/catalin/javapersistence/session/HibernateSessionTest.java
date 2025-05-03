package com.catalin.javapersistence.session;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.testing.transaction.TransactionUtil;
import org.hibernate.testing.transaction.TransactionUtil2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HibernateSessionTest {

        @Autowired
        EntityManagerFactory entityManagerFactory;

        @Test
        public void mixSessions() {
                TransactionUtil.doInJPA(() -> entityManagerFactory, entityManager -> {

                });
        }
}

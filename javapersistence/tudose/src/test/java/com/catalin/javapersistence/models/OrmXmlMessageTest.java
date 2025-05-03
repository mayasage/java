package com.catalin.javapersistence.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrmXmlMessageTest {

        @PersistenceUnit
        private EntityManagerFactory entityManagerFactory;

        @Test
        public void getSetMessage() {
                EntityManager entityManager = entityManagerFactory.createEntityManager();

                entityManager.getTransaction().begin();
                Message message = new Message();
                message.setMessageText("Hello World");
                message.setOrmXmlTestFieldString("ormXmlTestFieldString");
                entityManager.persist(message);
                entityManager.getTransaction().commit();

                List<Message> messages = entityManager.createQuery("SELECT m FROM Message m", Message.class).getResultList();

                Assertions.assertAll(
                        () -> Assertions.assertEquals(1, messages.size()),
                        () -> Assertions.assertEquals("ormXmlTestFieldString", messages.getFirst().getOrmXmlTestFieldString())
                );
        }
}

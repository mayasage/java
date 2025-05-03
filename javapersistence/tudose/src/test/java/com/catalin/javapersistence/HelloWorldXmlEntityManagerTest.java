package com.catalin.javapersistence;

import com.catalin.javapersistence.models.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HelloWorldXmlEntityManagerTest {

        @Test
        public void storeLoadMessage() {
                try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldXmlPersistenceUnit")) {
                        EntityManager em = emf.createEntityManager();

                        em.getTransaction().begin();
                        Message message = new Message();
                        message.setMessageText("Hello World");
                        em.persist(message);
                        em.getTransaction().commit();

                        em.getTransaction().begin();
                        List<Message> messages = em.createQuery("SELECT m FROM Message m", Message.class).getResultList();
                        messages.getLast().setMessageText("Hello World From EntityManager");
                        em.getTransaction().commit();

                        em.close();

                        Assertions.assertAll(
                                () -> Assertions.assertEquals(1, messages.size()),
                                () -> Assertions.assertEquals("Hello World From EntityManager", messages.getFirst().getMessageText())
                        );
                }
        }
}

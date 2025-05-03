package com.catalin.javapersistence;

import com.catalin.javapersistence.models.Message;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class HelloWorldJPATest {
        @PersistenceUnit
        private EntityManagerFactory entityManagerFactory;

        @Test
        public void storeLoadMessages() {
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                Session session = entityManager.unwrap(Session.class);
                System.out.println("Session: " + session);
                SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
                System.out.println("SessionFactory: " + sessionFactory);

                entityManager.getTransaction().begin();
                Message message = new Message();
                message.setMessageText("Hello World");
                entityManager.persist(message);
                entityManager.getTransaction().commit();

                entityManager.getTransaction().begin();
                List<Message> messages = entityManager.createQuery("SELECT m FROM Message m", Message.class).getResultList();
                messages.getLast().setMessageText("Hello World From JPA");
                entityManager.getTransaction().commit();

                Assertions.assertAll(
                        () -> Assertions.assertEquals(1, messages.size()),
                        () -> Assertions.assertEquals("Hello World From JPA", messages.getFirst().getMessageText())
                );
        }
}

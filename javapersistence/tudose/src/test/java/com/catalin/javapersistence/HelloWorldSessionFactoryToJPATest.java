package com.catalin.javapersistence;

import com.catalin.javapersistence.models.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloWorldSessionFactoryToJPATest {

        private static EntityManagerFactory entityManagerFactory() {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(Message.class);
                Map<String, String> properties = new HashMap<>();
                configuration.getProperties().putAll(properties);
                return Persistence.createEntityManagerFactory("HelloWorldXmlPersistenceUnit", properties); // Required persistence.xml file.
        }

        private static EntityManagerFactory entityManagerFactoryWithoutPersistenceXml() {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(Message.class);
                SessionFactory sessionFactory = configuration.buildSessionFactory();
                return sessionFactory.createEntityManager().getEntityManagerFactory();
        }

        @Test
        public void storeLoadMessage() {
                try (EntityManagerFactory entityManagerFactory = entityManagerFactoryWithoutPersistenceXml();
                     EntityManager entityManager = entityManagerFactory.createEntityManager()) {
                        entityManager.getTransaction().begin();
                        Message message = new Message();
                        message.setMessageText("Hello World");
                        entityManager.persist(message);
                        entityManager.getTransaction().commit();

                        entityManager.getTransaction().begin();
                        List<Message> messages = entityManager.createQuery("SELECT m FROM Message m", Message.class).getResultList();
                        messages.getLast().setMessageText("Hello World from EntityFactory");
                        entityManager.getTransaction().commit();

                        Assertions.assertAll(
                                () -> Assertions.assertEquals(1, messages.size()),
                                () -> Assertions.assertEquals("Hello World from EntityFactory", messages.getFirst().getMessageText())
                        );
                }
        }
}

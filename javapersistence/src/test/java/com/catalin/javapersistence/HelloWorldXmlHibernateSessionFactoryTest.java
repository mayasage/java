package com.catalin.javapersistence;

import com.catalin.javapersistence.models.Message;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HelloWorldXmlHibernateSessionFactoryTest {

        private static SessionFactory createSessionFactory() {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(Message.class);
                ServiceRegistry serviceRegistry =
                        new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties())
                                .build();
                return configuration.buildSessionFactory(serviceRegistry);
        }

        @Test
        public void storeLoadMessage() {
                try (SessionFactory sessionFactory = createSessionFactory();
                     Session session = sessionFactory.openSession()) {
                        session.beginTransaction();
                        Message message = new Message();
                        message.setMessageText("Hello World");
                        session.persist(message);
                        session.getTransaction().commit();

                        session.beginTransaction();
                        CriteriaBuilder builder = session.getCriteriaBuilder();
                        CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
                        Root<Message> root = criteria.from(Message.class);
                        criteria.where(builder.equal(root.get("messageText"), message.getMessageText()));
                        List<Message> messages = session.createQuery(criteria).getResultList();
                        messages.getLast().setMessageText("Hello World from SessionFactory");
                        session.getTransaction().commit();

                        Assertions.assertAll(
                                () -> Assertions.assertEquals(1, messages.size()),
                                () -> Assertions.assertEquals("Hello World from SessionFactory", messages.getFirst().getMessageText())
                        );
                }
        }
}

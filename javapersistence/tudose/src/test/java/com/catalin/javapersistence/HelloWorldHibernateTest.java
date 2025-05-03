package com.catalin.javapersistence;

import com.catalin.javapersistence.models.Message;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class HelloWorldHibernateTest {
        @PersistenceUnit
        private SessionFactory sessionFactory;

        @Test
        public void storeLoadMessage() {
                try (Session session = sessionFactory.openSession()) {
                        session.beginTransaction();
                        Message message = new Message();
                        message.setMessageText("Hello World From Hibernate");
                        session.persist(message);
                        session.getTransaction().commit();

                        session.beginTransaction();
                        CriteriaBuilder builder = session.getCriteriaBuilder();
                        CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
                        Root<Message> root = criteria.from(Message.class);
                        criteria.where(builder.equal(root.get("messageText"), message.getMessageText()));
                        List<Message> messages = session.createQuery(criteria).getResultList();
                        session.getTransaction().commit();

                        Assertions.assertAll(
                                () -> Assertions.assertEquals(1, messages.size()),
                                () -> Assertions.assertEquals("Hello World From Hibernate", messages.getFirst().getMessageText())
                        );
                }
        }
}

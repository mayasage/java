package com.catalin.javapersistence.dao;

import com.catalin.javapersistence.models.test.Bid;
import com.catalin.javapersistence.models.test.Bid1;
import com.catalin.javapersistence.models.test.Item1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//@Transactional
//public class Item1DaoImpl implements Item1Dao {
//
//        @PersistenceContext(type = PersistenceContextType.EXTENDED)
//        private EntityManager em;
//
//
//
//        @Override
//        public Item1 getById(Long id) {
//                return em.find(Item1.class, id);
//        }
//
//        @Override
//        public List<Item1> getAll() {
//                return em.createQuery("from Item1", Item1.class).getResultList();
//        }
//
//        @Override
//        public void insert(Item1 item) {
//                em.persist(item);
//                for (Bid1 bid : item.getBids()) {
//                        em.persist(bid);
//                }
//        }
//
//        @Override
//        public void updateName(Long id, String name) {
//                Item1 item = em.find(Item1.class, id);
//                item.setName(name);
//                em.persist(item);
//        }
//
//        @Override
//        public void delete(Item1 item) {
//                for (Bid1 bid : item.getBids()) {
//                        em.remove(bid);
//                }
//                em.remove(item);
//        }
//
//        @Override
//        public Item1 findByName(String name) {
//                return em
//                        .createQuery(
//                                "select i from Item1 i where i.name = :itemName",
//                                Item1.class
//                        )
//                        .setParameter("itemName", name)
//                        .getSingleResult();
//        }
//}

//@Repository
//@Transactional
//public class Item1DaoImpl extends AbstractGenericDao<Item1> {
//
//        public Item1DaoImpl() {
//                this.setClazz(Item1.class);
//        }
//
//        @Override
//        public void insert(Item1 item) {
//                em.persist(item);
//                for (Bid1 bid : item.getBids()) {
//                        em.persist(bid);
//                }
//        }
//
//        @Override
//        public void delete(Item1 item) {
//                for (Bid1 bid : item.getBids()) {
//                        em.remove(bid);
//                }
//                em.remove(item);
//        }
//}

@Repository
@RequiredArgsConstructor
public class Item1DaoImpl implements Item1Dao {

        private final SessionFactory sessionFactory;

        @Override
        public Item1 getById(Long id) {
                try (Session session = sessionFactory.openSession()) {
                        return session.get(Item1.class, id);
                }
        }

        @Override
        public List<Item1> getAll() {
                try (Session session = sessionFactory.openSession()) {
                        return session.createQuery("from Item1", Item1.class).list();
                }
        }

        @Override
        public void insert(Item1 item) {
                try (Session session = sessionFactory.openSession()) {
                        session.persist(item);
                        for (Bid1 bid1 : item.getBids()) {
                                session.persist(bid1);
                        }
                }
        }

        @Override
        public void update(Long id, String name) {
                System.out.println("update");
                try (Session session = sessionFactory.openSession()) {
                        Item1 item = session.get(Item1.class, id);
                        item.setName(name);
                        session.merge(item);
                        System.out.println("merged");
                }
        }

        @Override
        public void delete(Item1 item) {
                try (Session session = sessionFactory.openSession()) {
                        session
                                .createMutationQuery(
                                        "delete from Bid1 b where b.item.id = :itemId"
                                )
                                .setParameter("itemId", item.getId())
                                .executeUpdate();
                        session
                                .createMutationQuery(
                                        "delete from Item1 i where i.id = :itemId"
                                )
                                .setParameter("itemId", item.getId())
                                .executeUpdate();
                }
        }

        @Override
        public Item1 findByName(String name) {
                try (Session session = sessionFactory.openSession()) {
                        return
                                session
                                        .createQuery(
                                                "from Item1 where name = :name",
                                                Item1.class
                                        )
                                        .setParameter("name", name)
                                        .uniqueResult();
                }
        }
}

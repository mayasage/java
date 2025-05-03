package com.catalin.javapersistence.dao;

import com.catalin.javapersistence.models.test.Bid;
import com.catalin.javapersistence.models.test.Bid1;
import com.catalin.javapersistence.models.test.Bid1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

//@Repository
//@Transactional
//public class Bid1DaoImpl extends AbstractGenericDao<Bid1> {
//
//        public Bid1DaoImpl() {
//                this.setClazz(Bid1.class);
//        }
//}

@Repository
@Transactional
@RequiredArgsConstructor
public class Bid1DaoImpl implements Bid1Dao {

        private final SessionFactory sessionFactory;

        @Override
        public Bid1 getById(Long id) {
                try (Session session = sessionFactory.openSession()) {
                        return session.get(Bid1.class, id);
                }
        }

        @Override
        public List<Bid1> getAll() {
                try (Session session = sessionFactory.openSession()) {
                        return session.createQuery("from Bid1", Bid1.class).list();
                }
        }

        @Override
        public void insert(Bid1 bid) {
                try (Session session = sessionFactory.openSession()) {
                        session.persist(bid);
                }
        }

        @Override
        public void update(Long id, String value) {
                try (Session session = sessionFactory.openSession()) {
                        Bid1 bid = session.get(Bid1.class, id);
                        bid.setAmount(new BigDecimal(value));
                        session.merge(bid);
                }
        }

        @Override
        public void delete(Bid1 bid) {
                Session session = sessionFactory.getCurrentSession();
                        session.remove(bid);
        }

        @Override
        public List<Bid1> findByAmount(String value) {
                try (Session session = sessionFactory.openSession()) {
                        return session
                                .createQuery(
                                        "from Bid1 where amount = :amount",
                                        Bid1.class
                                )
                                .setParameter("amount", new BigDecimal(value))
                                .list();
                }
        }
}

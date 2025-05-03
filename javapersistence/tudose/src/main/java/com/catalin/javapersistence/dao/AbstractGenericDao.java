package com.catalin.javapersistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public abstract class AbstractGenericDao<T> implements GenericDao<T> {

        @PersistenceContext(type = PersistenceContextType.EXTENDED)
        protected EntityManager em;

        @Setter
        private Class<T> clazz;

        @Override
        public T getById(Long id) {
                return em.find(clazz, id);
        }

        @Override
        public List<T> getAll() {
                return
                        em
                                .createQuery(
                                        "select e from " + clazz.getName() + " e",
                                        clazz
                                )
                                .getResultList();
        }

        @Override
        public void insert(T entity) {
                em.persist(entity);
        }

        @Override
        public void delete(T entity) {
                em.remove(entity);
        }

        @Override
        public void update(Long id, String propertyName, Object propertyValue) {
                em
                        .createQuery(
                                "update " + clazz.getName() + " set " + propertyName + " = :propertyValue where id = :id"
                        )
                        .setParameter("propertyValue", propertyValue)
                        .setParameter("id", id)
                        .executeUpdate();
        }

        @Override
        public List<T> findByProperty(String propertyName, Object propertyValue) {
                return
                        em
                                .createQuery(
                                        "select e from " + clazz.getName() + " e where e." + propertyName + " = :propertyValue",
                                        clazz
                                )
                                .setParameter("propertyValue", propertyValue)
                                .getResultList();
        }
}

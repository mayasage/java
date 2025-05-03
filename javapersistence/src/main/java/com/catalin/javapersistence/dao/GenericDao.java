package com.catalin.javapersistence.dao;

import java.util.List;

public interface GenericDao<T> {

        T getById(Long id);

        List<T> getAll();

        void insert(T entity);

        void delete(T entity);

        void update(Long id, String propertyName, Object propertyValue);

        List<T> findByProperty(String propertyName, Object propertyValue);
}

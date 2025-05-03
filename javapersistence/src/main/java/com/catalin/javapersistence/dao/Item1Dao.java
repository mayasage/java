package com.catalin.javapersistence.dao;

import com.catalin.javapersistence.models.test.Item;
import com.catalin.javapersistence.models.test.Item1;

import java.util.List;

public interface Item1Dao {

        Item1 getById(Long id);

        List<Item1> getAll();

        void insert(Item1 item);

        void update(Long id, String name);

        void delete(Item1 item);

        Item1 findByName(String name);
}

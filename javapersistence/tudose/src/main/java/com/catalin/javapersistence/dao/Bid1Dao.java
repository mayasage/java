package com.catalin.javapersistence.dao;

import com.catalin.javapersistence.models.test.Bid;
import com.catalin.javapersistence.models.test.Bid1;

import java.math.BigDecimal;
import java.util.List;

public interface Bid1Dao {

        Bid1 getById(Long id);

        List<Bid1> getAll();

        void insert(Bid1 bid);

        void update(Long id, String value);

        void delete(Bid1 bid);

        List<Bid1> findByAmount(String value);
}

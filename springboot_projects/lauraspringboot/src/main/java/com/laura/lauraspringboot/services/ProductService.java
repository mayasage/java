package com.laura.lauraspringboot.services;

import com.laura.lauraspringboot.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
        private final List<Product> products = new ArrayList<>();

        public List<Product> findAll() {
                return products;
        }

        public void addOne(Product product) {
                products.add(product);
        }
}

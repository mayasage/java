package mayasage.spring_start_here.services;

import mayasage.spring_start_here.models.Product;
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

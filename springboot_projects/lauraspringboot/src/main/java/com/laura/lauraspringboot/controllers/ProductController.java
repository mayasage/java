package com.laura.lauraspringboot.controllers;

import com.laura.lauraspringboot.models.Product;
import com.laura.lauraspringboot.services.ProductService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Data
public class ProductController {
        private final ProductService productService;

        @GetMapping("/products")
        public String viewProducts(Model page) {
                List<Product> products = productService.findAll();
                page.addAttribute("products", products);
                return "products";
        }

        @PostMapping(path = "/products")
        public String addProduct(
                Product product,
                Model page
        ) {
                productService.addOne(product);
                List<Product> products = productService.findAll();
                page.addAttribute("products", products);
                return "products";
        }
}

package com.example.demo.service;


import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {
   private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable("products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Async
    public CompletableFuture<List<Product>> getAllProductsAsync() {
        return CompletableFuture.completedFuture(productRepository.findAll());
    }
}

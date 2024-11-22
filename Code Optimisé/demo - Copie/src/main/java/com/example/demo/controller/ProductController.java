package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.concurrent.CompletableFuture;
@RestController
@RequestMapping(value ="/api/products")
public class ProductController {
 private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/async")
    public CompletableFuture<List<Product>> getAllProductsAsync() {
        return productService.getAllProductsAsync();
    }

    @GetMapping("/page")
    public ResponseEntity<List<Product>> getProductsPage(@RequestParam int page, @RequestParam int size) {
        // Code pagination simplifié pour l'exemple
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products.subList(page * size, Math.min(products.size(), (page + 1) * size)));
    }
}

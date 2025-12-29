package com.noor170.culturalpro.controller;

import com.noor170.culturalpro.model.Product;
import com.noor170.culturalpro.model.Product.ProductType;
import com.noor170.culturalpro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) String type) {

        if (type == null || type.equalsIgnoreCase("ALL")) {
            return ResponseEntity.ok(productService.getAll());
        }

        try {
            ProductType productType = ProductType.valueOf(type.toUpperCase());
            return ResponseEntity.ok(productService.getByType(productType));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

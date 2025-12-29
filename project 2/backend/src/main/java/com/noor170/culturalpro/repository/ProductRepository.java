package com.noor170.culturalpro.repository;

import com.noor170.culturalpro.model.Product;
import com.noor170.culturalpro.model.Product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByType(ProductType type);
}

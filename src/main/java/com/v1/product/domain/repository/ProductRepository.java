package com.v1.product.domain.repository;

import com.v1.product.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    void save(Product product);

    Optional<Product> findById(Long id);
}

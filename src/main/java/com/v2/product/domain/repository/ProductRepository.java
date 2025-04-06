package com.v2.product.domain.repository;

import com.v2.product.domain.model.Product;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
     List<Product> findAll();
     Optional<Product> findById(Long id);
     void save(Product product);

     boolean existsByName(@NotEmpty String name);
}

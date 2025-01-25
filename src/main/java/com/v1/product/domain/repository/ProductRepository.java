package com.v1.product.domain.repository;

import com.v1.product.domain.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    void save(Product product);
}

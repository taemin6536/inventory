package com.v1.product.domain.repository;

import com.v1.product.domain.entity.Product;

import java.util.List;

public interface ProductQueryRepository {

    List<Product> findAll();
}

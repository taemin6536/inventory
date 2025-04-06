package com.v1.product.infra.repository;

import com.v1.product.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}

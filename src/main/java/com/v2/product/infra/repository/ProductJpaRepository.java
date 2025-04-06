package com.v2.product.infra.repository;


import com.v2.product.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

}

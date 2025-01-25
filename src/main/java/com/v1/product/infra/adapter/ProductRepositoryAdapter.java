package com.v1.product.infra.adapter;

import com.v1.product.domain.entity.Product;
import com.v1.product.domain.repository.ProductRepository;
import com.v1.product.infra.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }
}

package com.v1.product.infra.adapter;

import com.v1.product.domain.model.Product;
import com.v1.product.domain.repository.ProductRepository;
import com.v1.product.infra.repository.ProductJpaRepository;
import com.v1.product.infra.repository.ProductMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;
    private final ProductMybatisRepository productMybatisRepository;

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productJpaRepository.save(product);
    }
}

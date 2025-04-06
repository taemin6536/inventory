package com.v2.product.infra.adapter;

import com.v2.product.domain.model.Product;
import com.v2.product.domain.repository.ProductRepository;
import com.v2.product.infra.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }

    @Override
    public Optional<Product> findById(final Long id) {
        return productJpaRepository.findById(id);
    }

    @Override
    public void save(final Product product) {
        productJpaRepository.save(product);
    }

    @Override
    public boolean existsByName(final String name) {
        return productJpaRepository.existsByName(name);
    }

}

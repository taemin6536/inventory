package com.v1.product.application.service;

import com.v1.product.application.dto.command.CreateProductCommand;
import com.v1.product.application.dto.query.ProductQuery;
import com.v1.product.application.mapper.ProductQueryMapper;
import com.v1.product.domain.entity.Product;
import com.v1.product.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductQueryMapper productQueryMapper;


    public List<ProductQuery> getProductList() {
        final List<Product> product = productRepository.findAll();

        return productQueryMapper.productToQuery(product);
    }

    @Transactional
    public void createProduct(
            final CreateProductCommand createProductCommand
    ) {
        Product product = Product.builder()
                .name(createProductCommand.name())
                .description(createProductCommand.description())
                .build();

        productRepository.save(product);
    }

}

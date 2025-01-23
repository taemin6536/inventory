package com.v1.product.application.service;

import com.v1.product.application.dto.command.CreateProductCommand;
import com.v1.product.application.dto.query.ProductQuery;
import com.v1.product.application.mapper.ProductQueryMapper;
import com.v1.product.domain.entity.Product;
import com.v1.product.domain.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductQueryRepository productQueryRepository;
    private final ProductQueryMapper productCommandMapper;


    public List<ProductQuery> getProductList() {
        final List<Product> product = productQueryRepository.findAll();

        return productCommandMapper.productToQuery(product);
    }

    public void createProduct(
            final CreateProductCommand createProductCommand
    ) {

    }

}

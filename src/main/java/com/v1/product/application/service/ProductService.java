package com.v1.product.application.service;

import com.v1.global.common.exceptions.ProductNotFoundException;
import com.v1.product.api.dto.response.ProductQueryResponse;
import com.v1.product.application.dto.command.CreateProductCommand;
import com.v1.product.application.dto.query.ProductQuery;
import com.v1.product.application.mapper.ProductQueryMapper;
import com.v1.product.domain.entity.Category;
import com.v1.product.domain.model.Product;
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
                .category(
                        Category.from(createProductCommand.category())
                )
                .build();

        productRepository.save(product);
    }

    public ProductQueryResponse getProductById(Long id) {
        // 1. 아이디를 통해 제품 조회
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // 2. 조회된 제품 정보를 ProductQueryResponse로 변환 및 반환
        return null;
    }

    public List<ProductQuery> searchProducts(final String name, final String category, final Double minPrice, final Double maxPrice) {
        return null;
    }

    public boolean checkProductExists(final Long id) {
        return false;
    }
}

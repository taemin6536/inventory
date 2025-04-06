package com.v2.product.application.mapper;

import com.v2.product.api.dto.req.CreateProductRequest;
import com.v2.product.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(final CreateProductRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();
    }
}

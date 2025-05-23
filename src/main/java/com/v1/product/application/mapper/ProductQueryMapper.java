package com.v1.product.application.mapper;

import com.v1.product.application.dto.query.ProductQuery;
import com.v1.product.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductQueryMapper {

    public List<ProductQuery> productToQuery(List<Product> products) {
        return products.stream()
                .map(product -> new ProductQuery(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getStatus(),
                        product.getCategory().getValue()
                ))
                .collect(Collectors.toList());
    }
}

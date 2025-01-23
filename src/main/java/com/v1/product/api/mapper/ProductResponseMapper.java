package com.v1.product.api.mapper;

import com.v1.product.api.dto.response.ProductQueryResponse;
import com.v1.product.application.dto.query.ProductQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductResponseMapper {

    public List<ProductQueryResponse> productQueryToResponse(List<ProductQuery> productList) {
        return productList.stream()
                .map(productQuery -> new ProductQueryResponse(
                        productQuery.id(),
                        productQuery.name(),
                        productQuery.description(),
                        productQuery.status(),
                        productQuery.productCode()
                ))
                .collect(java.util.stream.Collectors.toList());
    }
}

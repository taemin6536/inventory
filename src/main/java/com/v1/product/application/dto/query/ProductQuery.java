package com.v1.product.application.dto.query;

import com.v1.global.common.enums.Status;
import com.v1.product.domain.model.ProductCode;

public record ProductQuery(
        Long id,
        String name,
        String description,
        Status status,
        ProductCode productCode
) {
}

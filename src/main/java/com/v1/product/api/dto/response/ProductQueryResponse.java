package com.v1.product.api.dto.response;

import com.v1.global.common.enums.Status;
import com.v1.product.domain.model.ProductCode;

public record ProductQueryResponse(
        Long id,
        String name,
        String description,
        Status status,
        ProductCode productCode
) {
}

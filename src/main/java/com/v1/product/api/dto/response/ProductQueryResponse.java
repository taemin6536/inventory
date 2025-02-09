package com.v1.product.api.dto.response;

import com.v1.global.common.enums.Status;

public record ProductQueryResponse(
        Long id,
        String name,
        String description,
        Status status,
        String category
) {
}

package com.v1.product.application.dto.query;

import com.v1.global.common.enums.Status;

public record ProductQuery(
        Long id,
        String name,
        String description,
        Status status,
        String category
) {
}

package com.v2.product.api.dto.req;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record UpdateProductRequest(
        @NotEmpty
        String name,
        @NotEmpty
        String description,
        @NotEmpty
        BigDecimal price
) {
}

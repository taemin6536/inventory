package com.v1.product.api.dto.request;

import jakarta.validation.constraints.NotEmpty;


public record CreateProductRequest(
        @NotEmpty String name,
        @NotEmpty String description,
        @NotEmpty String category
) {
}

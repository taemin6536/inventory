package com.v2.inventory.api.dto.req;

import jakarta.validation.constraints.Positive;

public record UpdateInventoryRequest(
        @Positive
        int availableQuantity
) {
}

package com.acme.orders.web.dto;

import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(
        @NotNull Long productId,
        int quantity
) {
}

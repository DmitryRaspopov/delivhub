package io.delivhub.deliveryservice.dtos.request.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateOrderItemRequest (
        @NotNull(message = "The quantity cannot be null.")
        @Positive(message = "The quantity cannot be negative.")
        Integer quantity,

        @NotNull(message = "The price at purchase cannot be null.")
        @Positive(message = "The price at purchase cannot be negative.")
        BigDecimal priceAtPurchase,

        Long orderId,
        Long productId
) {
}

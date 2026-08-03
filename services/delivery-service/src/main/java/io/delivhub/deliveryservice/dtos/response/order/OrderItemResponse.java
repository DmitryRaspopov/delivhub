package io.delivhub.deliveryservice.dtos.response.order;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderItemResponse(
        Long id,
        Integer quantity,
        BigDecimal priceAtPurchase,
        Long orderId,
        Long productId,
        String productName
) {
}

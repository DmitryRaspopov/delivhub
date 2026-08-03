package io.delivhub.deliveryservice.dtos.response.order;

import io.delivhub.storeservice.enums.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderResponse(
        Long id,
        BigDecimal totalAmount,
        String deliveryAddress,
        OrderStatus status,
        LocalDateTime createdAt,
        List<OrderItemResponse> orderItemResponseList
) {
    public OrderResponse {
        if (orderItemResponseList == null) {
            orderItemResponseList = List.of();
        }
    }
}

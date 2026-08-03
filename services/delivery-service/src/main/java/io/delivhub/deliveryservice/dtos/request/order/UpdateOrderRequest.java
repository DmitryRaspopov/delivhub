package io.delivhub.deliveryservice.dtos.request.order;

import io.delivhub.storeservice.dtos.response.OrderItemResponse;
import io.delivhub.storeservice.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record UpdateOrderRequest(
        @NotBlank(message = "The total amount cannot be empty.")
        @Positive(message = "The total amount must be above zero.")
        BigDecimal totalAmount,

        @NotBlank(message = "The deliveryAddress cannot be empty.")
        String deliveryAddress,

        @NotBlank(message = "The status cannot be empty.")
        OrderStatus status,

        List<OrderItemResponse> orderItemResponseList
) {

}

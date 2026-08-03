package io.delivhub.customerservice.dtos.request.customer;

import io.delivhub.storeservice.dtos.response.OrderResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateCustomerRequest(
        String name,
        String phoneNumber,
        String email,
        String avatarImageUrl,
        List<OrderResponse> orderResponseList
) {
    public CreateCustomerRequest {
        if (orderResponseList == null) {
            orderResponseList = List.of();
        }
    }
}

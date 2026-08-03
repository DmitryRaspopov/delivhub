package io.delivhub.deliveryservice.mappers;

import io.delivhub.storeservice.dtos.request.order.CreateOrderRequest;
import io.delivhub.storeservice.dtos.response.OrderResponse;
import io.delivhub.storeservice.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface OrderMapper {
    @Mapping(target = "orderItemResponseList", source = "orderItems")
    OrderResponse toDto(Order order);

    List<OrderResponse> toDtoList(List<Order> orders);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", source = "orderItemResponseList")
    Order toEntity(CreateOrderRequest createOrderRequest);
}

package io.delivhub.deliveryservice.mappers;

import io.delivhub.storeservice.dtos.request.order.CreateOrderItemRequest;
import io.delivhub.storeservice.dtos.response.OrderItemResponse;
import io.delivhub.storeservice.entities.Order;
import io.delivhub.storeservice.entities.OrderItem;
import io.delivhub.storeservice.entities.Product;
import io.delivhub.storeservice.repositories.OrderRepository;
import io.delivhub.storeservice.repositories.ProductRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    OrderItemResponse toDto(OrderItem orderItem);

    List<OrderItemResponse> toDtoList(List<OrderItem> orderItems);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", source = "orderId")
    @Mapping(target = "product", source = "productId")
    OrderItem toEntity(
            CreateOrderItemRequest dto,
            @Context OrderRepository orderRepository,
            @Context ProductRepository productRepository
    );

    default Order mapOrderId(Long orderId, @Context OrderRepository repository) {
        if (orderId == null) return null;
        return repository.getReferenceById(orderId);
    }

    default Product mapProductId(Long productId, @Context ProductRepository repository) {
        if (productId == null) return null;
        return repository.getReferenceById(productId);
    }
}

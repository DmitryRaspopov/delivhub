package io.delivhub.storeservice.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse (
        Long id,
        String name,
        String description,
        BigDecimal price,
        String imageUrl,
        Boolean isActive,
        DimensionsResponse dimensions,
        Integer inStock,
        Boolean available,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long categoryId
) {
}

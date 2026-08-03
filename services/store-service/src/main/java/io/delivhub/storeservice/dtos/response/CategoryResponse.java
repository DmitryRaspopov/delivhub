package io.delivhub.storeservice.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

public record CategoryResponse(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<Long> productIds
) {
}

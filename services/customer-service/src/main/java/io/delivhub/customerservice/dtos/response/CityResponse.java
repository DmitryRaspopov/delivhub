package io.delivhub.customerservice.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

public record CityResponse(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<Long> customerIds
) {
}

package io.delivhub.customerservice.dtos.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String phoneNumber,
        String avatarImageUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long cityId
) {
}

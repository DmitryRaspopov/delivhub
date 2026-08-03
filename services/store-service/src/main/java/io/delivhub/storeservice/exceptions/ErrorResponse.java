package io.delivhub.storeservice.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime time,
        int status,
        String error,
        String message,
        String path
) {
}

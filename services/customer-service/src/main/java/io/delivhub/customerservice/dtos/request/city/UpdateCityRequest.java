package io.delivhub.customerservice.dtos.request.city;

import jakarta.validation.constraints.NotBlank;

public record UpdateCityRequest(
        @NotBlank(message = "City name cannot be empty")
        String name
) {
}

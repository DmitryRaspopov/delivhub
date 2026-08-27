package io.delivhub.customerservice.dtos.request.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCityRequest(
        @NotBlank(message = "Caty name cannot be empty")
        @Size(max = 255, message = "City name must be less than 255 characters")
        String name
) {
}

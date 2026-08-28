package io.delivhub.customerservice.dtos.request.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCityRequest(
        @NotBlank(message = "{validation.city.name.notBlank}")
        @Size(max = 255, message = "{validation.city.name.size}")
        String name
) {
}

package io.delivhub.customerservice.dtos.request.city;

import jakarta.validation.constraints.NotBlank;

public record UpdateCityRequest(
        @NotBlank(message = "{validation.city.name.notBlank}")
        String name
) {
}

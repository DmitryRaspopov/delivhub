package io.delivhub.storeservice.dtos.request.category;

import jakarta.validation.constraints.Size;

public record UpdateCategoryRequest(
        @Size(max = 255, message = "{validation.category.name.size}")
        String name,

        @Size(max = 1000, message = "{validation.category.description.size}")
        String description
) {
}

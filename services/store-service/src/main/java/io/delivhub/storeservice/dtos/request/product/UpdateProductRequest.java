package io.delivhub.storeservice.dtos.request.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public record UpdateProductRequest(
        @Size(max = 255, message = "{validation.product.name.size}")
        String name,

        @Size(max = 1000, message = "{validation.product.description.size}")
        String description,

        @PositiveOrZero(message = "{validation.product.price.positiveOrZero}")
        BigDecimal price,

        @URL(message = "{validation.product.image.url}")
        String imageUrl,

        Boolean isActive,

        @Valid
        DimensionsRequest dimensions,

        @PositiveOrZero(message = "{validation.product.inStock.positiveOrZero}")
        Integer inStock,

        Boolean available,

        @Positive(message = "{validation.product.category.positive}")
        Long categoryId
) {
}

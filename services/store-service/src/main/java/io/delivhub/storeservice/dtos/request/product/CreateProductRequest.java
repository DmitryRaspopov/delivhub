package io.delivhub.storeservice.dtos.request.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "{validation.product.name.notBlank}")
        @Size(max = 255, message = "{validation.product.name.size}")
        String name,

        @Size(max = 1000, message = "{validation.product.description.size}")
        String description,

        @NotNull(message = "{validation.product.price.notNull}")
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

        @NotNull(message = "{validation.product.category.notNull}")
        @Positive(message = "{validation.product.category.positive}")
        Long categoryId
) {
}

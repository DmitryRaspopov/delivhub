package io.delivhub.storeservice.dtos.request.product;

import jakarta.validation.constraints.Positive;

public record DimensionsRequest(
        @Positive(message = "{validation.product.weight.positive}")
        Double weight,
        @Positive(message = "{validation.product.dimensions.positive}")
        Double height,
        @Positive(message = "{validation.product.dimensions.positive}")
        Double width,
        @Positive(message = "{validation.product.dimensions.positive}")
        Double length
) {
}

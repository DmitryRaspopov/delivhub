package io.delivhub.storeservice.dtos.response;

import jakarta.validation.constraints.Positive;

public record DimensionsResponse(
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

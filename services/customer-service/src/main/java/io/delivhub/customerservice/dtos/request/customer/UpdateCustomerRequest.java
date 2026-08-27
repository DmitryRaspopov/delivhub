package io.delivhub.customerservice.dtos.request.customer;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.NumberFormat;

@Builder
public record UpdateCustomerRequest(
        @NumberFormat(style = NumberFormat.Style.NUMBER)
        String phoneNumber,

        @URL(message = "Invalid image URL")
        String avatarImageUrl,

        @Positive(message = "Invalid city ID ")
        Long cityId
) {
}

package io.delivhub.customerservice.dtos.request.customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.NumberFormat;

@Builder
public record CreateCustomerRequest(
        @NotNull(message = "{validation.customer.phoneNumber.notNull}")
        @NumberFormat(style = NumberFormat.Style.NUMBER)
        String phoneNumber,

        @URL(message = "{validation.customer.avatarImageUrl.url}")
        String avatarImageUrl,

        @NotNull(message = "{validation.customer.city.notNull}")
        @Positive(message = "{validation.customer.city.positive}")
        Long cityId
) {

}

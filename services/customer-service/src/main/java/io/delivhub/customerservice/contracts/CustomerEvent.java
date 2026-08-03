package io.delivhub.customerservice.contracts;

import io.delivhub.customerservice.enums.CustomerEventType;

import java.util.UUID;

public record CustomerEvent(
        UUID customerID,
        CustomerEventType eventType,
        String firstName,
        String email
) {
}

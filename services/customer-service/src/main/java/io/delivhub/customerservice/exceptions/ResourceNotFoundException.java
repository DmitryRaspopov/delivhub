package io.delivhub.customerservice.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends CustomerServiceException {
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

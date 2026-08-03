package io.delivhub.storeservice.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends StoreServiceException {
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

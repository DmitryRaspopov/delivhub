package io.delivhub.storeservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StoreServiceException extends RuntimeException {
    private final HttpStatus status;

    public StoreServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

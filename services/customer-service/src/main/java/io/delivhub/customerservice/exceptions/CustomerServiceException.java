package io.delivhub.customerservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomerServiceException extends RuntimeException {
    private final HttpStatus status;

    public CustomerServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

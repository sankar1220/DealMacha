package com.dealmacha.businessdelegate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Constructor for ResourceNotFoundException
     */
    public ResourceNotFoundException() {
    }

    /**
     * Constructor for ResourceNotFoundException
     *
     * @param message
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }

    /**
     * Constructor for ResourceNotFoundException
     *
     * @param message
     * @param cause
     */
    public ResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for ResourceNotFoundException
     *
     * @param cause
     */
    public ResourceNotFoundException(final Throwable cause) {
        super(cause);
    }
}

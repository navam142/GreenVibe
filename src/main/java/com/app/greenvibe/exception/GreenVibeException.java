package com.app.greenvibe.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Base exception for all GreenVibe application exceptions.
 * Every custom exception extends this — the global handler only
 * needs to catch this one type to handle all of them uniformly.
 */
@Getter
public class GreenVibeException extends RuntimeException {

    private final HttpStatus status;

    public GreenVibeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

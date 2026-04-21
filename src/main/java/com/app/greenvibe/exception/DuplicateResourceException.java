package com.app.greenvibe.exception;

import org.springframework.http.HttpStatus;

/**
 * Thrown when attempting to create a resource that already exists.
 * Maps to HTTP 409 Conflict.
 *
 * Usage examples:
 *   throw new DuplicateResourceException("Customer", "email", email);
 *   throw new DuplicateResourceException("Category", "name", name);
 */
public class DuplicateResourceException extends GreenVibeException {

    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(
            String.format("%s already exists with %s: '%s'", resourceName, fieldName, fieldValue),
            HttpStatus.CONFLICT
        );
    }
}

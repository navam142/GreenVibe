package com.app.greenvibe.exception;

import org.springframework.http.HttpStatus;

/**
 * Thrown when a requested resource does not exist in the database.
 * Maps to HTTP 404 Not Found.
 *
 * Usage examples:
 *   throw new ResourceNotFoundException("Customer", "id", customerId);
 *   throw new ResourceNotFoundException("Product", "id", productId);
 *   throw new ResourceNotFoundException("Category", "name", categoryName);
 */
public class ResourceNotFoundException extends GreenVibeException {

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(
            String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue),
            HttpStatus.NOT_FOUND
        );
    }
}

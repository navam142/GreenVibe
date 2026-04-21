package com.app.greenvibe.exception;

import org.springframework.http.HttpStatus;

/**
 * Thrown when a customer tries to add to cart or place an order
 * but the requested quantity exceeds available stock.
 * Maps to HTTP 422 Unprocessable Entity.
 *
 * Usage example:
 *   throw new OutOfStockException(product.getName(), requested, product.getStockQuantity());
 */
public class OutOfStockException extends GreenVibeException {

    public OutOfStockException(String productName, int requested, int available) {
        super(
            String.format("Insufficient stock for '%s'. Requested: %d, Available: %d",
                    productName, requested, available),
            HttpStatus.UNPROCESSABLE_CONTENT
        );
    }
}

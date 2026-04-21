package com.app.greenvibe.exception;

import org.springframework.http.HttpStatus;

/**
 * Thrown when an order state transition is illegal —
 * e.g. cancelling an order that is already DELIVERED or SHIPPED.
 * Maps to HTTP 400 Bad Request.
 *
 * Usage example:
 *   throw new OrderOperationException(
 *       "Cannot cancel order with status: " + order.getOrderStatus());
 */
public class OrderOperationException extends GreenVibeException {

    public OrderOperationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}

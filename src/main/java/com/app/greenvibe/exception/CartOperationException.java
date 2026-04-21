package com.app.greenvibe.exception;

import org.springframework.http.HttpStatus;

/**
 * Thrown when a cart operation is invalid — e.g. removing an item
 * that is not in the cart, or placing an order from an empty cart.
 * Maps to HTTP 400 Bad Request.
 *
 * Usage examples:
 *   throw new CartOperationException("Cart is empty. Add items before placing an order.");
 *   throw new CartOperationException("Item not found in cart.");
 */
public class CartOperationException extends GreenVibeException {

    public CartOperationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}

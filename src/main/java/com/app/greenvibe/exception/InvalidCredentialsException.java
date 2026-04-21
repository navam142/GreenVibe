package com.app.greenvibe.exception;

import org.springframework.http.HttpStatus;

/**
 * Thrown during login when email/password combination is incorrect.
 * Maps to HTTP 401 Unauthorized.
 *
 * IMPORTANT: Always use a vague message like "Invalid email or password"
 * — never say "email not found" or "wrong password" separately,
 * as that leaks which part is wrong to attackers (user enumeration).
 */
public class InvalidCredentialsException extends GreenVibeException {

    public InvalidCredentialsException() {
        super("Invalid email or password", HttpStatus.UNAUTHORIZED);
    }
}

package com.app.greenvibe.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Standard error response body returned by the global exception handler.
 *
 * Example JSON for a single error:
 * {
 *   "timestamp": "2025-04-21 14:30:00",
 *   "status": 404,
 *   "error": "Not Found",
 *   "message": "Customer not found with id: '5'",
 *   "path": "/api/customers/5",
 *   "validationErrors": null
 * }
 *
 * Example JSON for a @Valid failure (multiple field errors):
 * {
 *   "timestamp": "2025-04-21 14:30:00",
 *   "status": 400,
 *   "error": "Bad Request",
 *   "message": "Validation failed",
 *   "path": "/api/customers/register",
 *   "validationErrors": [
 *     "email: Invalid email format",
 *     "password: Password must be at least 8 characters"
 *   ]
 * }
 */
@Getter
@Builder
public class ErrorResponseDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;

    private final int status;
    private final String error;
    private final String message;
    private final String path;

    // Only populated for @Valid failures — null otherwise
    private final List<String> validationErrors;
}

package com.app.greenvibe.exception;

import com.app.greenvibe.dto.response.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Centralised exception handler for the entire application.
 *
 * @RestControllerAdvice means this class intercepts exceptions thrown
 * from any @RestController before they reach the client.
 *
 * Three handlers cover every case you will encounter:
 *  1. GreenVibeException      — all your custom business exceptions
 *  2. MethodArgumentNotValidException — @Valid failures on request DTOs
 *  3. Exception               — safety net for anything unexpected
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all custom GreenVibe exceptions (ResourceNotFoundException,
     * OutOfStockException, DuplicateResourceException, etc.)
     * The HTTP status is read directly from the exception itself.
     */
    @ExceptionHandler(GreenVibeException.class)
    public ResponseEntity<ErrorResponseDto> handleGreenVibeException(
            GreenVibeException ex,
            HttpServletRequest request) {

        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(ex.getStatus().value())
                .error(ex.getStatus().getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    /**
     * Handles @Valid / @Validated failures on request DTOs.
     * Collects all field errors into a list so the client knows
     * every problem at once, not just the first one.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<String> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .sorted()
                .toList();

        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed")
                .path(request.getRequestURI())
                .validationErrors(validationErrors)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Safety net — catches anything not handled above.
     * Logs the real cause but returns a generic message to the client
     * so internal details are never leaked.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnexpectedException(
            Exception ex,
            HttpServletRequest request) {

        // In production, you would log ex with a proper logger here:
        // log.error("Unexpected error at {}: {}", request.getRequestURI(), ex.getMessage(), ex);

        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("An unexpected error occurred. Please try again later.")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.internalServerError().body(response);
    }
}

package com.app.greenvibe.exception;

import org.springframework.http.HttpStatus;

public class ImageUploadException extends GreenVibeException {

    public ImageUploadException(String message) {
        super(message, HttpStatus.BAD_GATEWAY);
    }

    public ImageUploadException(String message, Throwable cause) {
        super(message, HttpStatus.BAD_GATEWAY, cause);
    }
}

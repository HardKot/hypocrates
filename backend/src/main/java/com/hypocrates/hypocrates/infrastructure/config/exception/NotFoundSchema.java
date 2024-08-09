package com.hypocrates.hypocrates.infrastructure.config.exception;

public class NotFoundSchema extends RuntimeException {
    public NotFoundSchema(String message) {
        super(message);
    }
}

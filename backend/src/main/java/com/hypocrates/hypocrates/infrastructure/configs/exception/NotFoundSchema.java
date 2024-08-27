package com.hypocrates.hypocrates.infrastructure.configs.exception;

public class NotFoundSchema extends RuntimeException {
    public NotFoundSchema(String message) {
        super(message);
    }
}

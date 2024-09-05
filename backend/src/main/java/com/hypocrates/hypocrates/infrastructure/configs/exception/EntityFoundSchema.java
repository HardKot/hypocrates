package com.hypocrates.hypocrates.infrastructure.configs.exception;

public class EntityFoundSchema extends RuntimeException {
    public EntityFoundSchema(String message) {
        super(message);
    }
}

package com.hypocrates.hypocrates.infrastructure.config.exception;

public class DataNotUnique extends RuntimeException {
    public DataNotUnique(String message) {
        super(message);
    }
}

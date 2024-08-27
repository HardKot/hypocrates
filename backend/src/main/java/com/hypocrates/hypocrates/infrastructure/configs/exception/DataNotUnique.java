package com.hypocrates.hypocrates.infrastructure.configs.exception;

public class DataNotUnique extends RuntimeException {
    public DataNotUnique(String message) {
        super(message);
    }
}

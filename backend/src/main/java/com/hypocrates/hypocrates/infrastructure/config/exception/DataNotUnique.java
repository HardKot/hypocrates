package com.hypocrates.hypocrates.infrastructure.config.exception;

public class DataNotUnique extends RuntimeException {
    DataNotUnique(String message) {
        super(message);
    }
}

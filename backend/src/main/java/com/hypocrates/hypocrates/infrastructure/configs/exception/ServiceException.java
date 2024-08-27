package com.hypocrates.hypocrates.infrastructure.configs.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}

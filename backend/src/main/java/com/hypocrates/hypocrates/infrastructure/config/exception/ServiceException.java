package com.hypocrates.hypocrates.infrastructure.config.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}

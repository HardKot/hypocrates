package com.hypocrates.hypocrates.useCase;

import lombok.Getter;

@Getter
public class InteractError extends Throwable{
    public InteractError(String message) {
        super(message);
    }

    public InteractError() {
        super();
    }

    private int code;
}

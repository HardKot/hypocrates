package com.hypocrates.hypocrates.application.dto;

import jakarta.validation.constraints.NotEmpty;

public record ActivateEntityCode(
        @NotEmpty
        String entityKey,
        @NotEmpty
        String activationCode
) {
}

package com.hypocrates.hypocrates.infrastructure.dto;

import jakarta.validation.constraints.NotEmpty;

public record ConfirmedCode(
        @NotEmpty String code,
        @NotEmpty String id
) {
}

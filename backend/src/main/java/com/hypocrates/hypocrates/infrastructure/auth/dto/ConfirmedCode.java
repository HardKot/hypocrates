package com.hypocrates.hypocrates.infrastructure.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record ConfirmedCode(
        @NotEmpty String code,
        @NotEmpty String id
) {
}

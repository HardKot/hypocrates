package com.hypocrates.hypocrates.application.dto;

public record TokenDTO(
        String jwtToken,
        String refreshToken
) {
}

package com.hypocrates.hypocrates.application.dto;

import com.hypocrates.hypocrates.application.services.validator.UniqueClinicEmail.UniqueClinicEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record CreateClinicForm(
        @NotEmpty
        String name,

        @Email
        @NotEmpty
        @UniqueClinicEmail
        String clinicEmail,

        @Email
        @NotEmpty
        String staffEmail,

        @Min(8)
        @NotEmpty
        String password,

        @Min(8)
        @NotEmpty
        String confirmPassword
) {
}

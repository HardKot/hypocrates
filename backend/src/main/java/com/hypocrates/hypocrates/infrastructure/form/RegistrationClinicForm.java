package com.hypocrates.hypocrates.infrastructure.form;

import com.hypocrates.hypocrates.application.services.UniqueClinicEmail.UniqueClinicEmail;
import com.hypocrates.hypocrates.application.useCase.clinicInteract.dto.IClinicRegistration;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RegistrationClinicForm (
        @NotEmpty
        String name,

        @Size(min = 2, max = 50)
        @NotEmpty
        @Email
        @UniqueClinicEmail
        String clinicEmail,

        @NotEmpty
        @Email
        String staffEmail,

        @NotEmpty
        String password,

        @NotEmpty
        String confirmPassword
) implements IClinicRegistration {
}

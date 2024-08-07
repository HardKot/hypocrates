package com.hypocrates.hypocrates.infrastructure.clinic.dto;

import com.hypocrates.hypocrates.infrastructure.clinic.UniqueClinicEmail;
import com.hypocrates.hypocrates.useCase.clinicInteract.dto.IClinicRegistration;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RegistrationClinicForm (
        @NotEmpty
        String name,

        @NotEmpty
        @Email
        @UniqueClinicEmail
        String clinicEmail,

        @NotEmpty
        @Email
        String staffEmail
) implements IClinicRegistration {
}

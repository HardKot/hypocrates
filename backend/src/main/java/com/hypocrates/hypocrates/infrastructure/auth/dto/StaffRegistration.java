package com.hypocrates.hypocrates.infrastructure.auth.dto;

import com.hypocrates.hypocrates.useCase.staffInteract.dto.IStaffRegistration;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StaffRegistration(
        @Email @NotEmpty
        String email,
        @NotEmpty String password,
        @NotEmpty String confirmPassword
) implements IStaffRegistration {
}

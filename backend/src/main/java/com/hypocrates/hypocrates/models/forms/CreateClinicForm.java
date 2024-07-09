package com.hypocrates.hypocrates.models.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CreateClinicForm(
        @NotEmpty String firstname,
        @NotEmpty String lastname,
        String patronymic,

        @NotEmpty String name,
        @Email String String,
        String address
) {
}

package com.hypocrates.hypocrates.models.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;

public record CreateStaffForm(
        @NotEmpty String firstname,
        @NotEmpty String lastname,
        String patronymic,
        @Past String birthday,
        String avatarUrl,
        @Email String email,
        String phone
) {
}

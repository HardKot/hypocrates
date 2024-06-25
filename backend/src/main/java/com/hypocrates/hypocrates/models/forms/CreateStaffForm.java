package com.hypocrates.hypocrates.models.forms;

import com.hypocrates.hypocrates.useCase.RegistrationStaff.ICreateStaffForm;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;

import java.util.Date;

public record CreateStaffForm(
        @NotEmpty String firstname,
        @NotEmpty String lastname,
        String patronymic,
        @Past Date birthday,
        String avatarUrl,
        @Email String email,
        String phone,
        @NotEmpty String password,
        @NotEmpty String repeatPassword,
        String clinicName,
        String clinicAddress,
        String clinicAvatarUrl
) implements ICreateStaffForm {
}

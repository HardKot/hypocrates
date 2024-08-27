package com.hypocrates.hypocrates.infrastructure.form;

import java.util.Date;

import com.hypocrates.hypocrates.application.services.PhoneValidate.Phone;
import com.hypocrates.hypocrates.application.services.UniqueStaffEmail.UniqueStaffEmail;
import com.hypocrates.hypocrates.application.services.UniqueStaffPhone.UniqueStaffPhone;
import com.hypocrates.hypocrates.application.useCase.staffInteract.dto.IStaffRegistration;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record CreateStaffFullForm(
    @Email @NotEmpty @UniqueStaffEmail String email,

    @NotEmpty String firstname,

    @NotEmpty String lastname,

    @NotEmpty String patronymic,

    @NotNull @Past Date birthday,

    String avatarUrl,

    @Phone @UniqueStaffPhone String phone,

    @NotEmpty String password
    ) implements IStaffRegistration {
}

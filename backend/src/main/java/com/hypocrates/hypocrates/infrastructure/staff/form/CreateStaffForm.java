package com.hypocrates.hypocrates.infrastructure.staff.form;

import java.util.Date;

import com.hypocrates.hypocrates.infrastructure.common.Phone;
import com.hypocrates.hypocrates.infrastructure.staff.validate.UniqueStaffEmail;
import com.hypocrates.hypocrates.infrastructure.staff.validate.UniqueStaffPhone;
import com.hypocrates.hypocrates.useCase.staffInteract.dto.IStaffRegistration;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;

public record CreateStaffForm(
    @Email @NotEmpty @UniqueStaffEmail String email,

    @NotEmpty String firstname,

    @NotEmpty String lastname,

    @NotEmpty String patronymic,

    @NotEmpty @Past Date birthday,

    String avatarUrl,

    @Phone @UniqueStaffPhone String phone,

    @NotEmpty String password,

    @NotEmpty String confirmPassword)
    implements IStaffRegistration {

}

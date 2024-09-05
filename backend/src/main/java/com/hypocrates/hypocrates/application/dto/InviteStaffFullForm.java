package com.hypocrates.hypocrates.application.dto;

import com.hypocrates.hypocrates.application.services.validator.PhoneValidate.Phone;
import com.hypocrates.hypocrates.application.services.validator.UniqueStaffEmail.UniqueStaffEmail;
import com.hypocrates.hypocrates.application.services.validator.UniqueStaffPhone.UniqueStaffPhone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.util.Date;

public record InviteStaffFullForm(
    @Email @NotEmpty @UniqueStaffEmail String email,

    @NotEmpty String firstname,

    @NotEmpty String lastname,

    @NotEmpty String patronymic,

    @NotNull @Past Date birthday,

    String avatarUrl,

    @Phone @UniqueStaffPhone String phone
    ) implements IInvitedStaffForm {
    @Override
    public String password() {
        return null;
    }

    public String confirmPassword() {
        return null;
    }
}

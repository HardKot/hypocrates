package com.hypocrates.hypocrates.application.dto;

import java.util.Date;

import com.hypocrates.hypocrates.application.services.validator.PhoneValidate.Phone;
import com.hypocrates.hypocrates.application.services.validator.UniqueStaffEmail.UniqueStaffEmail;
import com.hypocrates.hypocrates.application.services.validator.UniqueStaffPhone.UniqueStaffPhone;
import jakarta.validation.constraints.*;

public record CreateStaffFullForm(
    @Email @NotEmpty @UniqueStaffEmail String email,

    @NotEmpty String firstname,

    @NotEmpty String lastname,

    @NotEmpty String patronymic,

    @NotNull @Past Date birthday,

    String avatarUrl,

    @Phone @UniqueStaffPhone String phone,

    @Min(8)
    @NotEmpty String password,

    @Min(8)
    @NotEmpty String confirmPassword
    ) implements IInvitedStaffForm {
}

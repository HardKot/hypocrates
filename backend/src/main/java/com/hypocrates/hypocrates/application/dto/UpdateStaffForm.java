package com.hypocrates.hypocrates.application.dto;

import com.hypocrates.hypocrates.application.services.validator.PhoneValidate.Phone;
import com.hypocrates.hypocrates.application.services.validator.UniqueStaffEmail.UniqueStaffEmail;
import com.hypocrates.hypocrates.application.services.validator.UniqueStaffPhone.UniqueStaffPhone;
import jakarta.validation.constraints.*;

import java.util.Date;

public record UpdateStaffForm(
    @Email @UniqueStaffEmail String email,

    String firstname,

    String lastname,

    String patronymic,

    @Past Date birthday,

    String avatarUrl,

    @Phone @UniqueStaffPhone String phone,

    @Min(8)
    String password,

    @Min(8)
    String confirmPassword
    ) implements IInvitedStaffForm {
}

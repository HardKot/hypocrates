package com.hypocrates.hypocrates.application.dto;

import com.hypocrates.hypocrates.application.services.validator.UniqueStaffEmail.UniqueStaffEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record InviteStaffShortForm(
    @Email @NotEmpty @UniqueStaffEmail String email
    ) implements IInvitedStaffForm {
    @Override
    public String firstname() {
        return null;
    }

    @Override
    public String lastname() {
        return null;
    }

    @Override
    public String patronymic() {
        return null;
    }

    @Override
    public Date birthday() {
        return null;
    }

    @Override
    public String avatarUrl() {
        return null;
    }

    @Override
    public String phone() {
        return null;
    }

    @Override
    public String password() {
        return null;
    }

    @Override
    public String confirmPassword() {
        return null;
    }
}

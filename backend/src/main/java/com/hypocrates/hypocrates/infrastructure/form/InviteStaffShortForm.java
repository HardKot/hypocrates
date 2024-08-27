package com.hypocrates.hypocrates.infrastructure.form;

import com.hypocrates.hypocrates.application.services.UniqueStaffEmail.UniqueStaffEmail;
import com.hypocrates.hypocrates.application.useCase.staffInteract.dto.IStaffRegistration;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record InviteStaffShortForm(
    @Email @NotEmpty @UniqueStaffEmail String email
    ) implements IStaffRegistration {
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
    public String phone() {
        return null;
    }

    @Override
    public String password() {
        return null;
    }
}

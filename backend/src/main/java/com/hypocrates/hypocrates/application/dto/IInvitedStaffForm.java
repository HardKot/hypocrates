package com.hypocrates.hypocrates.application.dto;

import java.util.Date;

public interface IInvitedStaffForm {
    String email();

    String firstname();

    String lastname();

    String patronymic();

    Date birthday();

    String avatarUrl();

    String phone();

    String password();

    String confirmPassword();
}

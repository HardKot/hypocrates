package com.hypocrates.hypocrates.useCase.RegistrationStaff;

import java.util.Date;

public interface ICreateStaffForm {
    String firstname();

    String lastname();

    String patronymic();

    Date birthday();

    String avatarUrl();

    String email();

    String phone();

    String password();
    String repeatPassword();

    String clinicName();
    String clinicAddress();
    String clinicAvatarUrl();

}

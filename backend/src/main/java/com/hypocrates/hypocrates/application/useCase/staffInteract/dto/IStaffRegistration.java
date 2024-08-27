package com.hypocrates.hypocrates.application.useCase.staffInteract.dto;

import java.util.Date;

public interface IStaffRegistration {
    String firstname();
    String lastname();
    String patronymic();

    Date birthday();
    String phone();
    String email();
    String password();
}

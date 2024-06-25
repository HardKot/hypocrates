package com.hypocrates.hypocrates.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class Patient extends User {
    private Clinic clinic;

    public Patient(Long id, String firstname, String lastname, String patronymic, Date birthday, String avatarUrl, String email, String phone, Boolean emailIsActive, Boolean phoneIsActive, Clinic clinic, String password) {
        super(id, firstname, lastname, patronymic, birthday, avatarUrl, email, phone, emailIsActive, phoneIsActive, password);
        this.clinic = clinic;
    }

}

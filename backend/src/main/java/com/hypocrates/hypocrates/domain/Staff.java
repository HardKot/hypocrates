package com.hypocrates.hypocrates.domain;

import lombok.*;

import java.util.Date;

@Getter
public class Staff extends User {
    private Clinic clinic;

    private StaffRole role;

    public Staff(Long id, String firstname, String lastname, String patronymic, Date birthday, String avatarUrl, String email, String phone, Boolean emailIsActive, Boolean phoneIsActive, Clinic clinic, StaffRole role, String password) {
        super(id, firstname, lastname, patronymic, birthday, avatarUrl, email, phone, emailIsActive, phoneIsActive, password);
        this.clinic = clinic;
        this.role = role;
    }

}

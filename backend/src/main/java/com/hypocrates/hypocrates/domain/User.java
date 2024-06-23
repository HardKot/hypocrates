package com.hypocrates.hypocrates.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public abstract class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private Date birthday;
    private String avatarUrl;

    private String email;
    private String phone;

    private Boolean emailIsActive;
    private Boolean phoneIsActive;

    private String password;

    public Boolean getIsActive() {
        return emailIsActive;
    }

    public Boolean updatePassword(String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) return false;
        this.password = password;
        return true;
    }

    public String getFullName() {
        var fullName = new StringBuilder();
        fullName.append(firstname).append(" ").append(lastname);
        if (patronymic != null) fullName.append(" ").append(patronymic);
        return fullName.toString();
    }
}

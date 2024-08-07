package com.hypocrates.hypocrates.entity.user;

import com.hypocrates.hypocrates.entity.AbstractEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class UserModel extends AbstractEntity {
    protected String firstname;
    protected String lastname;
    protected String patronymic;
    protected Date birthday;
    protected String avatarUrl;

    protected String email;
    protected String phone;

    protected boolean emailIsActive;
    protected boolean phoneIsActive;

    protected String password;

//    public UserModel(UUID id, String firstname, String lastname, String patronymic, Date birthday, String avatarUrl, String email, String phone, boolean emailIsActive, boolean phoneIsActive, String password, Date updateAt, Date createAt) {
//        super(id, updateAt, createAt);
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.patronymic = patronymic;
//        this.birthday = birthday;
//        this.avatarUrl = avatarUrl;
//        this.email = email;
//        this.phone = phone;
//        this.emailIsActive = emailIsActive;
//        this.phoneIsActive = phoneIsActive;
//        this.password = password;
//    }

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

package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractUser extends AbstractClinicEntity {
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

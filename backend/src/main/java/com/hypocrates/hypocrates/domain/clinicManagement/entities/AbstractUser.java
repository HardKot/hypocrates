package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractUser extends AbstractClinicEntity {
    private String firstname;
    private String lastname;
    private String patronymic;
    private Date birthday;

    @Column(name = "avatar_url")
    private String avatarUrl;

    private String email;
    private String phone;

    @Column(name = "email_is_active")
    private boolean emailIsActive;

    @Column(name = "phone_is_active")
    private boolean phoneIsActive;

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

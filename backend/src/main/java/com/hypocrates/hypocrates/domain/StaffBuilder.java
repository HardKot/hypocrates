package com.hypocrates.hypocrates.domain;


import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true) @Setter
public class StaffBuilder {
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
    private Clinic clinic;
    private StaffRole role;
    private String password;

    public StaffBuilder setUser(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.patronymic = user.getPatronymic();
        this.birthday = user.getBirthday();
        this.avatarUrl = user.getAvatarUrl();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.emailIsActive = user.getEmailIsActive();
        this.phoneIsActive = user.getPhoneIsActive();

        this.password = user.getPassword();
        return this;
    }

    public Staff createStaff() {
        return new Staff(id, firstname, lastname, patronymic, birthday, avatarUrl, email, phone, emailIsActive, phoneIsActive, clinic, role, password);
    }
}

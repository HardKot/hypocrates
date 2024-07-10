package com.hypocrates.hypocrates.database.schema;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@Entity
@Table(name = "appuser")
@EqualsAndHashCode(callSuper = true)
@SecondaryTable(name = "UserContact", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@SecondaryTable(name = "UserSecurity", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class UserSchema extends BaseSchema {
    private String firstname;
    private String lastname;
    private String patronymic;
    private Date birthday;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(table = "UserContact", name = "email")
    private String email;

    @Column(table = "UserContact", name = "emailIsActive")
    private Boolean emailIsActive;

    @Column(table = "UserContact", name = "phone")
    private String phone;
    @Column(table = "UserContact", name = "phoneIsActive")
    private Boolean phoneIsActive;
    @Column(table = "UserSecurity", name = "password")
    private String password;
}

package com.hypocrates.hypocrates.database.schema;

import com.hypocrates.hypocrates.database.adminSchema.ClinicSchema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table
@Entity
@EqualsAndHashCode(callSuper = true)
@SecondaryTable(name = "AppUser", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "UserContact", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "UserSecurity", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class PatientSchema extends BaseSchema {
    @Column(table = "AppUser", name = "firstname")
    private String firstname;

    @Column(table = "AppUser", name = "lastname")
    private String lastname;

    @Column(table = "AppUser", name = "patronymic")
    private String patronymic;

    @Column(table = "AppUser", name = "birthday")
    private String date;

    @Column(table = "AppUser", name = "avatar_url")
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

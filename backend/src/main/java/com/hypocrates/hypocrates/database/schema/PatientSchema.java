package com.hypocrates.hypocrates.database.schema;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@EqualsAndHashCode(callSuper = true)
@SecondaryTable(name = "AppUser", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "UserEmail", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "UserPhone", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
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

    @Column(table = "UserEmail", name = "email")
    private String email;

    @Column(table = "UserEmail", name = "emailIsActive")
    private Boolean emailIsActive;

    @Column(table = "UserPhone", name = "phone")
    private String phone;

    @Column(table = "UserPhone", name = "phoneIsActive")
    private Boolean phoneIsActive;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private ClinicSchema clinic;
}

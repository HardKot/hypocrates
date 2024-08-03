package com.hypocrates.hypocrates.database.clinics.schema;

import com.hypocrates.hypocrates.database.BaseSchema;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "patient_schema")
public class PatientSchema extends BaseSchema {
    private String firstname;

    private String lastname;

    private String patronymic;

    private String date;

    @Column(name = "avatar_url")
    private String avatarUrl;

    private String email;

    @Column(name = "email_is_active")
    private Boolean emailIsActive;

    private String phone;

    @Column(name = "phone_is_active")
    private Boolean phoneIsActive;

    private String password;
}

package com.hypocrates.hypocrates.database.schema;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
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

package com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema;

import com.hypocrates.hypocrates.infrastructure.config.database.BaseSchema;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@Setter
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

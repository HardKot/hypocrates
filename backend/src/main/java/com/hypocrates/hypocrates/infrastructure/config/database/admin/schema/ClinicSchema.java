package com.hypocrates.hypocrates.infrastructure.config.database.admin.schema;

import com.hypocrates.hypocrates.entity.clinic.ClinicStatus;
import com.hypocrates.hypocrates.infrastructure.config.database.BaseSchema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import lombok.*;

import java.util.Objects;

@Entity(name = "Clinic")
@Setter
@Getter
@NoArgsConstructor
public class ClinicSchema extends BaseSchema {
    private String code;
    private String name;
    private String address;

    @Column(name = "avatar_url")
    private String avatarUrl;
    private String email;
    private ClinicStatus status;
}

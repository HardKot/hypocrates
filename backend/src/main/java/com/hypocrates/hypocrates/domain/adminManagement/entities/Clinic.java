package com.hypocrates.hypocrates.domain.adminManagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Clinic extends AbstractAdminEntity {
    private String code;
    private String name;
    private String address;

    @Column(name = "avatar_url")
    private String avatarUrl;
    private String email;

    private ClinicStatus status = ClinicStatus.NO_ACTIVE;

    public boolean isNew() {
        return status == ClinicStatus.NO_ACTIVE;
    }

    public enum ClinicStatus {
        NO_ACTIVE,
        ACTIVE,
    }

    public static Clinic demoClinic() {
        return Clinic.builder()
                .code("demo")
                .name("Демо")
                .address("г. Екатеринбург")
                .email("demo@hypocrates.com")
                .status(ClinicStatus.ACTIVE)
                .build();
    }
}

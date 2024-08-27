package com.hypocrates.hypocrates.domain.adminManagement.entities;

import com.hypocrates.hypocrates._entities.clinic.ClinicStatus;
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
    private String avatarUrl;
    private String email;

    private ClinicStatus status;

    public boolean isNew() {
        return status == ClinicStatus.NO_ACTIVE;
    }
}

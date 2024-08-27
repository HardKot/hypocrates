package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class ClinicConfiguration extends AbstractClinicEntity {
    private String key;

    private String value;
}
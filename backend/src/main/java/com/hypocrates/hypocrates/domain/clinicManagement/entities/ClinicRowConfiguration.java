package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@Entity(name = "clinic_row_configuration")
@NoArgsConstructor
public class ClinicRowConfiguration extends AbstractClinicEntity{
    private String key;

    private String value;
}
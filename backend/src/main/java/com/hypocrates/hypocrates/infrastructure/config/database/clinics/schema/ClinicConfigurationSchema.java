package com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema;

import com.hypocrates.hypocrates.infrastructure.config.database.BaseSchema;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ClinicConfiguration")
public class ClinicConfigurationSchema extends BaseSchema {
    private String key;
    private String value;
}
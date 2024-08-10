package com.hypocrates.hypocrates.infrastructure.clinic.dto;

import com.hypocrates.hypocrates.entity.clinic.IClinicConfiguration;
import lombok.Builder;

@Builder
public record ClinicConfiguration(
        boolean privateRegistration
) implements IClinicConfiguration {
}

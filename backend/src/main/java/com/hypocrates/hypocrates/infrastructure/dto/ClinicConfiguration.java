package com.hypocrates.hypocrates.infrastructure.dto;

import com.hypocrates.hypocrates._entities.clinic.IClinicConfiguration;
import lombok.Builder;

@Builder
public record ClinicConfiguration(
        boolean privateRegistration
) implements IClinicConfiguration {
}

package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ClinicConfiguration {
    private boolean enabledRegistration;


    static public ClinicConfiguration getClinicFactory(Set<ClinicRowConfiguration> rows) {
        var builder = ClinicConfiguration.builder();
        rows.forEach(row -> {
            switch (row.getKey()) {
                case "enabledRegistration":
                        builder.enabledRegistration(row.getValue().equals("1"));
                        break;
                }
            });
        return builder.build();
    }
}
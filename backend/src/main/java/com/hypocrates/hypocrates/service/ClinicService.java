package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import com.hypocrates.hypocrates.database.repository.ClinicConfigRepository;
import com.hypocrates.hypocrates.database.schema.ClinicConfigSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
@RequiredArgsConstructor
public class ClinicService {
    private ClinicConfigRepository configRepository;
    private Environment environment;
    private RandomString randomString;

    public Clinic getClinic() {
        var clinicConfigs = configRepository.findAll();
        if (clinicConfigs.isEmpty()) {
            return null;
        }

        var clinicBuilder = Clinic.builder()
                .codeID(environment.getProperty("CLINIC_CODE"))
                .name(getValue("clinic_name"))
                .address(getValue("clinic_address"))
                .avatarUrl(getValue("clinic_avatar_url"));

        return clinicBuilder.build();
    }


    private String getValue(String name) {
        var config = configRepository.findByName(name);
        return config.map(ClinicConfigSchema::getValue).orElse(null);
    }
}

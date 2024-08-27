package com.hypocrates.hypocrates.application.services;

import com.hypocrates.hypocrates._entities.clinic.IClinicConfiguration;
import com.hypocrates.hypocrates.infrastructure.dto.ClinicConfiguration;
import com.hypocrates.hypocrates.repositories.ClinicConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClinicConfigurationService {
    private final ClinicConfigurationRepository clinicConfigurationRepository;


    public IClinicConfiguration getClinicConfiguration() {
        var clinicConfigurationBuilder = ClinicConfiguration.builder();

        clinicConfigurationRepository.findAll().forEach(item -> {
            if (item.getKey().equals("privateRegistration")) {
                clinicConfigurationBuilder.privateRegistration(
                        item.getValue().equals("1")
                );
            }
        });

        return clinicConfigurationBuilder.build();
    }
}

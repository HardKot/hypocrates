package com.hypocrates.hypocrates.infrastructure.clinic;

import com.hypocrates.hypocrates.entity.clinic.IClinicConfiguration;
import com.hypocrates.hypocrates.infrastructure.clinic.dto.ClinicConfiguration;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository.ClinicConfigurationRepository;
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

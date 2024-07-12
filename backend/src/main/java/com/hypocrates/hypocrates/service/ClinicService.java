package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import com.hypocrates.hypocrates.database.repository.ConfigRepository;
import com.hypocrates.hypocrates.database.schema.ConfigSchema;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ClinicService {
    private ConfigRepository configRepository;
    private Environment environment;
    private RandomString randomString;

    public Clinic getClinic() {
        var clinicConfigs = configRepository.findAll();
        if (clinicConfigs.isEmpty()) {
            return null;
        }

        var clinicBuilder = Clinic.builder()
                .codeID(environment.getProperty(CLINIC_CODE))
                .name(getValueByKey(CLINIC_NAME))
                .address(getValueByKey(CLINIC_ADDRESS))
                .avatarUrl(getValueByKey(CLINIC_AVATAR_URL));

        return clinicBuilder.build();
    }

    public void saveClinic(Clinic clinic) {
        setValueByKey(CLINIC_NAME, clinic.getName());
        setValueByKey(CLINIC_ADDRESS, clinic.getAddress());
        setValueByKey(CLINIC_AVATAR_URL, clinic.getAvatarUrl());
    }

    private String getValueByKey(String key) {
        return configRepository.findByName(key).getValue();
    }

    private void setValueByKey(String key, String value) {
        var config = configRepository.findByName(key);
        if (config == null) {
            config = new ConfigSchema();
            config.setValue(key);
        }
        config.setValue(value);
        configRepository.save(config);
    }

    private static final String CLINIC_CODE = "CLINIC_CODE";
    private static final String CLINIC_NAME = "clinic_name";
    private static final String CLINIC_ADDRESS = "clinic_address";
    private static final String CLINIC_AVATAR_URL = "clinic_avatar_url";
}

package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.context.ClinicContext;
import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import com.hypocrates.hypocrates.core.domain.clinic.ClinicStatus;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ClinicService {
    private Environment environment;
    private RandomString randomString;
    private ClinicContext clinicContext;

    public Clinic getClinic() {


        var clinicBuilder = Clinic.builder()
                .status(ClinicStatus.ACTIVE)
                .codeID(clinicContext.getClinicCode())
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
        return null;
    }

    private void setValueByKey(String key, String value) {

    }

    private static final String CLINIC_NAME = "clinic_name";
    private static final String CLINIC_ADDRESS = "clinic_address";
    private static final String CLINIC_AVATAR_URL = "clinic_avatar_url";
}

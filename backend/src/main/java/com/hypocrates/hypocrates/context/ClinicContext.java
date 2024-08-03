package com.hypocrates.hypocrates.context;

import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ClinicContext {
    private final ThreadLocal<String> treadClinicCode = new ThreadLocal<>();


    public void setClinic(Clinic clinic) {
        treadClinicCode.set(clinic.getCodeID());
    }

    public String getClinicCode() {
        var clinicCode = treadClinicCode.get();
        if (clinicCode == null) {
            clinicCode = "0";
        }
        return clinicCode;
    }
}

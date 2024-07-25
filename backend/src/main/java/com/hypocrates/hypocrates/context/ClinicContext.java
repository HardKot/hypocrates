package com.hypocrates.hypocrates.context;

import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ClinicContext {
    private Environment environment;
    private ThreadLocal<String> treadClinicCode;

    public ClinicContext(Environment environment) {
        this.environment = environment;
        treadClinicCode = new ThreadLocal<>();
    }


    public void setClinic(Clinic clinic) {
        treadClinicCode.set(clinic.getCodeID());
    }

    public String getClinicCode() {
        var clinicCode = treadClinicCode.get();
        if (clinicCode == null) {
            clinicCode = environment.getProperty(CLINIC_CODE);
        }
        if (clinicCode == null) {
            clinicCode = "000000";
        }
        return clinicCode;
    }

    private static final String CLINIC_CODE = "CLINIC_CODE";
}

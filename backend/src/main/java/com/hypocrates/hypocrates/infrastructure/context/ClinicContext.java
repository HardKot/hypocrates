package com.hypocrates.hypocrates.infrastructure.context;

import com.hypocrates.hypocrates.configs.database.admin.schema.Clinic;
import org.springframework.stereotype.Component;

@Component
public class ClinicContext {
    static private final ThreadLocal<String> treadClinicCode = new ThreadLocal<>();


    static public void setClinic(Clinic clinic) {
        treadClinicCode.set(clinic.getCode());
    }

    static public void setClinicCode(String clinicCode) {
        treadClinicCode.set(clinicCode);
    }

    static public String getClinicCode() {
        var clinicCode = treadClinicCode.get();
        if (clinicCode == null) {
            clinicCode = "demo";
        }
        return clinicCode;
    }
}

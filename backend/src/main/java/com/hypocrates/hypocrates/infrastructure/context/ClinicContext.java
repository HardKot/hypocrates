package com.hypocrates.hypocrates.infrastructure.context;

import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import org.springframework.stereotype.Component;

@Component
public class ClinicContext {
    static private final ThreadLocal<Clinic> treadClinic = new ThreadLocal<>();


    static public void setClinic(Clinic clinic) {
        treadClinic.set(clinic);
    }

    static public Clinic getClinic() {
        Clinic clinic = treadClinic.get();
        if (clinic == null) {
            clinic = Clinic.demoClinic();
        }
        return clinic;
    }
}

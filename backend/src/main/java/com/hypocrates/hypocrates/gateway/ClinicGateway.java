package com.hypocrates.hypocrates.gateway;

import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import com.hypocrates.hypocrates.core.domain.clinic.IClinicGateway;
import com.hypocrates.hypocrates.service.ClinicService;
import com.hypocrates.hypocrates.service.EmailSender;
import com.hypocrates.hypocrates.service.RandomString;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClinicGateway implements IClinicGateway {
    private RandomString randomString;
    private EmailSender emailSender;
    private Environment environment;
    private ClinicService clinicService;

    @Override
    public Clinic getClinic() {
        return clinicService.getClinic();
    }

    @Override
    public void sendEmail(String email, String message) {
        emailSender.sendEmail(email, message);
    }

    @Override
    public String getEmailOwner() {
        return environment.getProperty("OWNER_EMAIL");
    }

    @Override
    public String getRandomString() {
        return randomString.nextString();
    }

    @Override
    public Clinic saveClinic(Clinic clinic) {
        return null;
    }

    @Override
    public String getHostName() {
        return environment.getProperty("HOST_NAME");
    }
}

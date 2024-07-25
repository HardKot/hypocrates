package com.hypocrates.hypocrates.core.domain.clinic;


public interface IClinicGateway {
    Clinic getClinic();

    void sendEmail(String email, String message);

    String getRandomString();

    Clinic saveClinic(Clinic clinic);

    String getHostName();
}

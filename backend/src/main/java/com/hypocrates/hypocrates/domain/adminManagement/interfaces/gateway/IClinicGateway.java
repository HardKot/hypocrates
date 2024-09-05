package com.hypocrates.hypocrates.domain.adminManagement.interfaces.gateway;

import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.interfaces.IEmailSenderGateway;
import com.hypocrates.hypocrates.interfaces.IArrayValueStorage;
import com.hypocrates.hypocrates.interfaces.IJwtTokenBuilder;
import com.hypocrates.hypocrates.interfaces.ITemplateBuilder;

import java.util.Optional;

public interface IClinicGateway {
    void createClinicDatabase(String clinicCode);

    String getRandomString(int length);

    Clinic saveClinic(Clinic clinic);

    Staff saveStaff(Staff staff);

    IArrayValueStorage getArrayValueStorage(String key);

    IJwtTokenBuilder tokenBuilder();

    boolean sendMail(String to, String body);

    ITemplateBuilder templateBuilder();

    Optional<Clinic> getClinicByCode(String clinicCode);

    String encryptPassword(String password);

    IEmailSenderGateway emailSender();
}

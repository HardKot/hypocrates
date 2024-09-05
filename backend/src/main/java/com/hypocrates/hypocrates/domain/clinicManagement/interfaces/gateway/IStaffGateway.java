package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.gateway;

import com.hypocrates.hypocrates.application.dto.IInvitedStaffForm;
import com.hypocrates.hypocrates.application.dto.UpdateStaffForm;
import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.ClinicConfiguration;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.interfaces.IArrayValueStorage;
import com.hypocrates.hypocrates.interfaces.IEmailSenderGateway;
import com.hypocrates.hypocrates.interfaces.IJwtTokenBuilder;
import com.hypocrates.hypocrates.interfaces.ITemplateBuilder;

import java.util.Optional;


public interface IStaffGateway {
    String getRandomString(int length);

    Staff saveStaff(Staff staff);

    IArrayValueStorage getArrayValueStorage(String key);

    IJwtTokenBuilder tokenBuilder();

    boolean sendMail(String to, String body);

    ITemplateBuilder templateBuilder();

    Optional<Staff> getStaffByEmail(String email);

    Staff mapFormToSchema(IInvitedStaffForm form);

    Clinic getCurrentClinic();

    ClinicConfiguration getClinicConfiguration();

    Staff mapFormToSchema(UpdateStaffForm form, Staff entity);

    String encryptPassword(String password);

    IEmailSenderGateway emailSender();

    String extractUsernameFromToken(String token);
    String extractTargetFromToken(String token);
}

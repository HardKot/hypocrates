package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.gateway;

import com.hypocrates.hypocrates.application.dto.IInvitedStaffForm;
import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.ClinicConfiguration;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.interfaces.IArrayValueStorage;
import com.hypocrates.hypocrates.interfaces.IJwtServiceFacade;
import com.hypocrates.hypocrates.interfaces.IJwtTokenBuilder;
import com.hypocrates.hypocrates.interfaces.ITemplateBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

public interface IAuthGateway {
    Staff findStaffByEmail(String email);

    String encryptPassword(String password);

    IJwtTokenBuilder tokenBuilder();

    IArrayValueStorage getArrayValueStorage(String key);

    UUID extractUUIDFromToken(String token);

    boolean validateToken(String token);

    String extractUsernameFromToken(String token);
}

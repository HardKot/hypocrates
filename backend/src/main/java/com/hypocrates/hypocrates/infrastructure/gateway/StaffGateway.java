package com.hypocrates.hypocrates.infrastructure.gateway;

import com.hypocrates.hypocrates.application.dto.IInvitedStaffForm;
import com.hypocrates.hypocrates.application.dto.UpdateStaffForm;
import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.ClinicConfiguration;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.gateway.IStaffGateway;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.mapper.IStaffMapper;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.IClinicRowConfigurationRepository;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.IStaffRepository;
import com.hypocrates.hypocrates.infrastructure.context.ClinicContext;
import com.hypocrates.hypocrates.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@Component
@RequiredArgsConstructor
public class StaffGateway implements IStaffGateway {
    private final IRandomFacade randomFacade;
    private final IStaffRepository staffRepository;
    private final IEmailSenderFacade emailSenderFacade;
    private final IJwtServiceFacade jwtServiceFacade;
    private final IKeyValueStorage keyValueStorage;
    private final ITemplateFacade templateFacade;
    private final IStaffMapper staffMapper;
    private final IClinicRowConfigurationRepository configurationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String getRandomString(int length) {
        return randomFacade.randomString(length);
    }

    @Override
    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public IArrayValueStorage getArrayValueStorage(String key) {
        return keyValueStorage.arrayValueStorage(key);
    }

    @Override
    public IJwtTokenBuilder tokenBuilder() {
        return jwtServiceFacade.buildToken();
    }


    @Override
    public boolean sendMail(String to, String body) {
        return emailSenderFacade.sendMail(to, body);
    }


    @Override
    public ITemplateBuilder templateBuilder() {
        try {
            return templateFacade.templateBuilder();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<Staff> getStaffByEmail(String email) {
        return staffRepository.findByEmail(email);
    }

    @Override
    public Staff mapFormToSchema(IInvitedStaffForm form) {
        return staffMapper.formToSchema(form);
    }



    @Override
    public Clinic getCurrentClinic() {
        return ClinicContext.getClinic();
    }

    @Override
    public ClinicConfiguration getClinicConfiguration() {
        return ClinicConfiguration.getClinicFactory(configurationRepository.findAll());
    }

    @Override
    public Staff mapFormToSchema(UpdateStaffForm form, Staff entity) {
        return staffMapper.formToEntity(form, entity);
    }

    @Override
    public String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public IEmailSenderGateway emailSender() {
        try {
            return new EmailSenderGateway(templateFacade.templateBuilder(), emailSenderFacade);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public String extractUsernameFromToken(String token) {
        return jwtServiceFacade.extractUsername(token);
    }

    @Override
    public String extractTargetFromToken(String token) {
        return jwtServiceFacade.extractTarget(token);
    }
}

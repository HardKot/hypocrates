package com.hypocrates.hypocrates.infrastructure.gateway;

import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import com.hypocrates.hypocrates.domain.adminManagement.interfaces.repository.IClinicRepository;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.domain.adminManagement.interfaces.gateway.IClinicGateway;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.IStaffRepository;
import com.hypocrates.hypocrates.interfaces.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Component
public class ClinicGateway implements IClinicGateway {
    private final IStaffRepository staffRepository;
    private final IClinicRepository clinicRepository;
    private final IRandomFacade randomFacade;
    private final IEmailSenderFacade emailSenderFacade;
    private final IJwtServiceFacade jwtServiceFacade;
    private final IKeyValueStorage keyValueStorage;
    private final ITemplateFacade templateFacade;

    private final Function<Clinic, Void> createClinicDatabase;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ClinicGateway(
            IStaffRepository staffRepository,
            IClinicRepository clinicRepository,
            IRandomFacade randomFacade,
            IEmailSenderFacade emailSenderFacade,
            IJwtServiceFacade jwtServiceFacade,
            IKeyValueStorage keyValueStorage,
            ITemplateFacade templateFacade,
            @Qualifier("createClinicDatabase") Function<Clinic, Void> createClinicDatabase,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.staffRepository = staffRepository;
        this.clinicRepository = clinicRepository;
        this.randomFacade = randomFacade;
        this.emailSenderFacade = emailSenderFacade;
        this.jwtServiceFacade = jwtServiceFacade;
        this.keyValueStorage = keyValueStorage;
        this.templateFacade = templateFacade;
        this.createClinicDatabase = createClinicDatabase;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void createClinicDatabase(Clinic clinic) {
        this.createClinicDatabase.apply(clinic);
    }

    @Override
    public String getRandomString(int length) {
        return randomFacade.randomString(length);
    }

    @Override
    public Clinic saveClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
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
    public Optional<Clinic> getClinicByCode(String clinicCode) {
        return clinicRepository.findByCode(clinicCode);
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
}

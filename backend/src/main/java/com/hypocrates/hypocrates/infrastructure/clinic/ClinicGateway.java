package com.hypocrates.hypocrates.infrastructure.clinic;

import com.hypocrates.hypocrates.entity.clinic.ClinicModel;
import com.hypocrates.hypocrates.entity.clinic.IClinicGateway;
import com.hypocrates.hypocrates.infrastructure.ClinicContext;
import com.hypocrates.hypocrates.infrastructure.config.database.admin.repository.ClinicSchemaRepository;
import com.hypocrates.hypocrates.infrastructure.config.database.admin.repository.ConfirmedCodeRepository;
import com.hypocrates.hypocrates.infrastructure.config.database.admin.schema.ConfirmedCodeSchema;
import com.hypocrates.hypocrates.infrastructure.libs.EmailSenderLib;
import com.hypocrates.hypocrates.infrastructure.libs.RandomLib;
import com.hypocrates.hypocrates.infrastructure.libs.TemplateLibs;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
@AllArgsConstructor
public class ClinicGateway implements IClinicGateway {
    private final ClinicContext clinicContext;
    private RandomLib randomLib;
    private EmailSenderLib emailSender;
    private TemplateLibs templateLibs;

    private ClinicSchemaRepository clinicSchemaRepository;
    private ConfirmedCodeRepository confirmedCodeRepository;

    private ClinicMapper clinicMapper;

    @Qualifier("createClinicDatabase")
    private Function<String, Void> createClinicDatabase;


    @Override
    public ClinicModel getClinicByEmail(String email) {
        return clinicSchemaRepository.findByEmail(email).map(clinicMapper::schemaToModel).orElse(null);
    }

    @Override
    public boolean isExistingClinic(String email) {
        return clinicSchemaRepository.existsByCode(email);
    }

    @Override
    public String sendActiveEmail(ClinicModel clinic) throws TemplateException, IOException {
        var code = new ConfirmedCodeSchema();
        code.setCode(randomLib.randomCode());
        var message = templateLibs.getBody("ConfirmationEmailClinic", Map.of("code", code.getCode()));
        emailSender.sendEmail(clinic.getEmail(), message);
        code = confirmedCodeRepository.save(code);
        return code.getId().toString();
    }

    @Override
    public String getEmptyClinicCode() {
        var code = randomLib.randomLowerChars(6);
        var isExists = clinicSchemaRepository.existsByCode(code);
        if (isExists) return getEmptyClinicCode();
        return code;
    }

    @Override
    public ClinicModel createClinic(ClinicModel clinic) {
        var clinicSchema = clinicMapper.modelToSchema(clinic);
        clinicSchema = clinicSchemaRepository.save(clinicSchema);
        createClinicDatabase.apply(clinicSchema.getCode());

        return clinicMapper.schemaToModel(clinicSchema);
    }
}

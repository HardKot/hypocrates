package com.hypocrates.hypocrates.application.services;

import com.hypocrates.hypocrates.application.dto.ActivateEntityCode;
import com.hypocrates.hypocrates.application.dto.CreateClinicForm;
import com.hypocrates.hypocrates.application.exception.ClinicServiceException;
import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.StaffRole;
import com.hypocrates.hypocrates.infrastructure.configs.exception.EntityFoundSchema;
import com.hypocrates.hypocrates.interfaces.IArrayValueStorage;
import com.hypocrates.hypocrates.domain.adminManagement.interfaces.gateway.IClinicGateway;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ClinicService {
    private final IClinicGateway clinicGateway;

    private final IArrayValueStorage activateClinicCodeStorage = clinicGateway.getArrayValueStorage("clinicActivate");

    public Result<Clinic, ClinicServiceException> createClinic(@Valid CreateClinicForm form) {
        if (!form.password().equals(form.confirmPassword())) {
            return Results.failure(new ClinicServiceException("Пароли не совпадают"));
        }

        var clinicCode = clinicGateway.getRandomString(6);

        var clinic = Clinic.builder()
                .code(clinicCode)
                .name(form.name())
                .email(form.clinicEmail())
                .build();

        var staff = Staff.builder()
                .email(form.staffEmail())
                .role(StaffRole.Owner())
                .password(clinicGateway.encryptPassword(form.password()))
                .build();

        clinic = clinicGateway.saveClinic(clinic);
        clinicGateway.createClinicDatabase(clinicCode);
        clinicGateway.saveStaff(staff);

        return Results.success(clinic);
    }

    public Result<Clinic, ClinicServiceException> activateClinic(Clinic clinic) {
        clinic.setStatus(Clinic.ClinicStatus.ACTIVE);
        clinicGateway.saveClinic(clinic);
        return Results.success(clinic);
    }

    public Result<ActivateEntityCode, ClinicServiceException> sendActivateEmail(Clinic clinic) {
        if (clinic.getStatus() == Clinic.ClinicStatus.ACTIVE) {
            return Results.failure(new ClinicServiceException("Clinic is active."));
        }

        ActivateEntityCode activateCode = new ActivateEntityCode(
                clinic.getCode(),
                clinicGateway.getRandomString(6)
        );


        String token = clinicGateway.tokenBuilder()
                .payload("clinicCode", clinic.getCode())
                .payload("target", "activateClinic")
                .payload("code", activateCode.activationCode())
                .build();

        if (!clinicGateway
                .emailSender()
                .email(clinic.getEmail())
                .template("activateClinic")
                .payload("clinicName", clinic.getName())
                .payload("code", activateCode.activationCode())
                .payload("token", token)
                .send()
                ) {

            return Results.failure(new ClinicServiceException("Error when send mail"));
        }

        activateClinicCodeStorage
                .add(activateCode);


        return Results.success(activateCode);
    }

    public Result<Clinic, ClinicServiceException> activateClinicByCode(@Valid ActivateEntityCode activateClinicCode) {
        Clinic clinic = clinicGateway.getClinicByCode(activateClinicCode.entityKey()).orElseThrow(() -> new EntityFoundSchema("Клиника не найдена"));

        if (activateClinicCodeStorage.has(activateClinicCode)) {
            clinic.setStatus(Clinic.ClinicStatus.ACTIVE);
            clinicGateway.saveClinic(clinic);
            clinicGateway.emailSender()
                    .email(clinic.getEmail())
                    .template("successActivateCode")
                    .payload("clinicName", clinic.getName())
                    .send();

        }

        return Results.success(clinic);
    }
}

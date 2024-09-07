package com.hypocrates.hypocrates.application.services;

import com.hypocrates.hypocrates.application.dto.ActivateEntityCode;
import com.hypocrates.hypocrates.application.dto.IInvitedStaffForm;
import com.hypocrates.hypocrates.application.dto.UpdateStaffForm;
import com.hypocrates.hypocrates.application.exception.StaffServiceException;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.StaffRole;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.gateway.IStaffGateway;

import com.hypocrates.hypocrates.infrastructure.configs.exception.EntityFoundSchema;
import com.hypocrates.hypocrates.interfaces.IArrayValueStorage;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class StaffService {
    private final IStaffGateway staffGateway;
    private final IArrayValueStorage activateStaffCodeStorage ;

    public StaffService(IStaffGateway staffGateway) {
        this.staffGateway = staffGateway;
        this.activateStaffCodeStorage = staffGateway.getArrayValueStorage("staffActivate");
    }


    public Result<ActivateEntityCode, StaffServiceException> sendActivateEmail(Staff staff) {
        if (staff.getIsActive()) {
            return Results.failure(new StaffServiceException("Staff is active."));
        }

        ActivateEntityCode activateCode = new ActivateEntityCode(
                staff.getEmail(),
                staffGateway.getRandomString(6)
        );


        String token = staffGateway.tokenBuilder()
                .payload("email", staff.getEmail())
                .payload("target", "activateStaff")
                .payload("code", activateCode.activationCode())
                .build();

        if (!staffGateway.emailSender()
                .email(staff.getEmail())
                .template("activateStaff")
//                .payload("clinicName", staff.getClinic().getName())
                .payload("firstname", staff.getFirstname())
                .payload("lastname", staff.getLastname())
                .payload("patronymic", staff.getPatronymic())
                .payload("code", activateCode.activationCode())
                .payload("token", token)
                .send()
        ) {
            return Results.failure(new StaffServiceException("Error when send mail"));
        }

        activateStaffCodeStorage
                .add(activateCode);


        return Results.success(activateCode);
    }

    public Result<Staff, StaffServiceException> activateStaffByCode(@Valid ActivateEntityCode activateCode) {
        Staff staff = staffGateway.getStaffByEmail(activateCode.entityKey()).orElseThrow(() -> new EntityFoundSchema("Staff not found."));

        if (activateStaffCodeStorage.has(activateCode)) {
            staff.setEmailIsActive(true);
            staffGateway.saveStaff(staff);

            staffGateway.emailSender()
                    .email(staff.getEmail())
                    .template("activateStaff")
//                    .payload("clinicName", staff.getClinic().getName())
                    .payload("firstname", staff.getFirstname())
                    .payload("lastname", staff.getLastname())
                    .payload("patronymic", staff.getPatronymic())
                    .send();
        }

        return Results.success(staff);
    }

    public Result<Staff, StaffServiceException> activateStaffByToken(@NotEmpty String token) {
        String target = staffGateway.extractTargetFromToken(token);
        if (!target.equals("activateStaff")) {
            return Results.failure(new StaffServiceException("Not support token."));
        }

        String email = staffGateway.extractUsernameFromToken(token);
        Staff staff = staffGateway.getStaffByEmail(email).orElse(null);

        if (staff == null) {
            return Results.failure(new StaffServiceException("Staff not found."));
        }
        staff.setEmailIsActive(true);
        staffGateway.saveStaff(staff);

        return Results.success(staff);
    }

    public Result<Staff, StaffServiceException> invitedStaff(@Valid IInvitedStaffForm form, Staff manager) {
        if (!manager.hasAccess(StaffRole.AppRule.INVITED_STAFF)) return Results.failure(new StaffServiceException("Manager no has access."));



        Staff staff = staffGateway.mapFormToSchema(form);
        staff.setInvitedStaff(manager);
//        staff.setClinic(manager.getClinic());

        return createStaff(staff);
    }

    public Result<Staff, StaffServiceException> registrationStaff(@Valid IInvitedStaffForm form) {
        if (!staffGateway.getClinicConfiguration().isEnabledRegistration()) {
            return Results.failure(new StaffServiceException("Регистрация в клинике отключенна"));
        }

        if (!form.password().equals(form.confirmPassword())) {
            return Results.failure(new StaffServiceException("Пароли не совпадают"));
        }
        Staff staff = staffGateway.mapFormToSchema(form);
//        staff.setClinic(staffGateway.getCurrentClinic());
        staff.setPassword(staffGateway.encryptPassword(form.password()));

        return createStaff(staff);
    }

    private Result<Staff, StaffServiceException> createStaff(Staff staff) {
        staffGateway.saveStaff(staff);

        ActivateEntityCode activateCode = new ActivateEntityCode(
                staff.getEmail(),
                staffGateway.getRandomString(6)
        );

        String token = staffGateway.tokenBuilder()
                .payload("staffEmail", staff.getEmail())
                .payload("target", "activateStaff")
                .payload("code", activateCode.activationCode())
                .build();

        if (!staffGateway.sendMail(staff.getEmail(), staffGateway.templateBuilder()
                .name("invitedStaff")
//                .payload("clinicName", staff.getClinic().getName())
                .payload("firstname", staff.getFirstname())
                .payload("lastname", staff.getLastname())
                .payload("patronymic", staff.getPatronymic())
                .payload("token", token)
                .build()
        )
        ) {
            return Results.failure(new StaffServiceException("Error when send mail"));
        }

        activateStaffCodeStorage
                .add(activateCode);

        return Results.success(staff);
    }

    public Result<Staff, StaffServiceException> updateStaff(@Valid UpdateStaffForm form, Staff staff) {
        if (form.password() != null && !form.password().equals(form.confirmPassword())) {
            staff.setPassword(form.password());
        }

        staff = staffGateway.mapFormToSchema(form, staff);
        staffGateway.saveStaff(staff);

        return Results.success(staff);
    }


}

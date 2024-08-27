package com.hypocrates.hypocrates.application.useCase.clinicInteract;

import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import com.hypocrates.hypocrates._entities.clinic.IClinicGateway;
import com.hypocrates.hypocrates._entities.staff.IStaffGateway;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.StaffRole;
import com.hypocrates.hypocrates.application.useCase.InteractError;
import com.hypocrates.hypocrates.application.useCase.clinicInteract.dto.IClinicRegistration;
import com.hypocrates.hypocrates.application.useCase.clinicInteract.dto.ClinicRegistrationResult;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class ClinicInteract {
    private IClinicGateway clinicGateway;
    private IStaffGateway staffGateway;

    public Result<ClinicRegistrationResult, InteractError> registrationClinic(IClinicRegistration dto) {
        if (!dto.password().equals(dto.confirmPassword())) return Results.failure(new ClinicInteractError("Пароли не совпадают"));

        var clinicCode = clinicGateway.getEmptyClinicCode();

        var clinic = Clinic.builder()
                .code(clinicCode)
                .name(dto.name())
                .email(dto.clinicEmail())
                .build();

        var administratorRole = StaffRole.Owner();
        var staff = Staff.builder()
                .email(dto.staffEmail())
                .build();

        clinic = clinicGateway.createClinic(clinic);
        if (clinic == null) {
            return Results.failure(new ClinicInteractError.ClinicNoCreated());
        }
        administratorRole = staffGateway.getStaffRoleByName("Owner");
        staff.setRole(administratorRole);
        staff.setPassword(staffGateway.passwordEncoder(dto.password()));
        staff = staffGateway.saveStaff(staff);
        String clinicActivateCode;
        String staffActivateCode;
        try {
            clinicActivateCode = clinicGateway.sendActiveEmail(clinic);
            staffActivateCode = staffGateway.sendActiveEmail(staff);
        } catch (TemplateException | IOException e) {
            return Results.failure(new ClinicInteractError.ErrorSenderEmail());
        }

        return Results.success(new ClinicRegistrationResult(clinicActivateCode, staffActivateCode));
    }
}
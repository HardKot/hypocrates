package com.hypocrates.hypocrates.useCase.clinicInteract;

import com.hypocrates.hypocrates.entity.staff.StaffModel;
import com.hypocrates.hypocrates.entity.clinic.ClinicModel;
import com.hypocrates.hypocrates.entity.clinic.IClinicGateway;
import com.hypocrates.hypocrates.entity.staff.IStaffGateway;
import com.hypocrates.hypocrates.entity.staff.StaffRoleModel;
import com.hypocrates.hypocrates.useCase.InteractError;
import com.hypocrates.hypocrates.useCase.clinicInteract.dto.IClinicRegistration;
import com.hypocrates.hypocrates.useCase.clinicInteract.dto.ClinicRegistrationResult;
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
        var clinicCode = clinicGateway.getEmptyClinicCode();

        var clinic = ClinicModel.builder()
                .code(clinicCode)
                .name(dto.name())
                .email(dto.clinicEmail())
                .build();

        var administratorRole = StaffRoleModel.Owner();
        var staff = StaffModel.builder()
                .email(dto.staffEmail())
                .build();

        clinic = clinicGateway.createClinic(clinic);
        if (clinic == null) {
            return Results.failure(new ClinicInteractError.ClinicNoCreated());
        }
        administratorRole = staffGateway.getStaffRoleByName("Owner");
        staff.setRole(administratorRole);
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
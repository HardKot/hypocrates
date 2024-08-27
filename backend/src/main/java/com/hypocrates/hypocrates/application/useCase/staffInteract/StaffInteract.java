package com.hypocrates.hypocrates.application.useCase.staffInteract;

import com.hypocrates.hypocrates._entities.clinic.IClinicGateway;
import com.hypocrates.hypocrates._entities.staff.IStaffGateway;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.application.useCase.InteractError;
import com.hypocrates.hypocrates.application.useCase.staffInteract.dto.IStaffRegistration;
import com.hypocrates.hypocrates.application.useCase.staffInteract.dto.StaffRegistrationResult;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class StaffInteract {
    private final IStaffGateway staffGateway;
    private final IClinicGateway clinicGateway;

    public Result<StaffRegistrationResult, InteractError> registration(IStaffRegistration dto, Staff invited) {
        if (invited == null && clinicGateway.clinicConfiguration().privateRegistration())
            return Results.failure(new InteractError("У организации включена приватная регистрация"));

        Staff staff = staffGateway.getStaffByRegistrationForm(dto);

        staff = staffGateway.saveStaff(staff);

        try {
            var code = staffGateway.sendActiveEmail(staff);
            return Results.success(new StaffRegistrationResult(code));
        } catch (TemplateException | IOException e) {
            return Results.failure(new InteractError("Ошибка при отправке сообщения"));
        }
    }
}

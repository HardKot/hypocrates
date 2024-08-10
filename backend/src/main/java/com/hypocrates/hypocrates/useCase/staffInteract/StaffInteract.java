package com.hypocrates.hypocrates.useCase.staffInteract;

import com.hypocrates.hypocrates.entity.clinic.IClinicGateway;
import com.hypocrates.hypocrates.entity.staff.IStaffGateway;
import com.hypocrates.hypocrates.entity.staff.StaffModel;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.infrastructure.config.exception.DataNotUnique;
import com.hypocrates.hypocrates.infrastructure.config.exception.ServiceException;
import com.hypocrates.hypocrates.useCase.InteractError;
import com.hypocrates.hypocrates.useCase.staffInteract.dto.IStaffRegistration;
import com.hypocrates.hypocrates.useCase.staffInteract.dto.StaffRegistrationResult;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class StaffInteract {
    private IStaffGateway staffGateway;
    private IClinicGateway clinicGateway;

    public Result<StaffRegistrationResult, InteractError> registration(IStaffRegistration dto) {
        if (clinicGateway.clinicConfiguration().privateRegistration())
            return Results.failure(new InteractError("У организации включена приватная регистрация"));

        if (!dto.password().equals(dto.confirmPassword()))
            return Results.failure(new InteractError("Пароли не совподают"));

        StaffModel staff = StaffModel.builder()
                .email(dto.email())
                        .password(staffGateway.passwordEncoder(dto.password())).build();

        staff = staffGateway.saveStaff(staff);
        try {
            var code = staffGateway.sendActiveEmail(staff);
            return Results.success(new StaffRegistrationResult(code));
        } catch (TemplateException | IOException e) {
            return Results.failure(new InteractError("Ошибка при отправке сообщения"));
        }
    }
}

package com.hypocrates.hypocrates.useCase.RegistrationStaff;

import com.hypocrates.hypocrates.domain.*;
import com.hypocrates.hypocrates.useCase.StaffInteractError;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import lombok.Setter;


public class RegistrationStaffUseCase {
    @Setter
    private IStaffGateway staffGateway;

    @Setter
    private IClinicGateway clinicGateway;

    public Result<Staff, StaffInteractError> execute(ICreateStaffForm form) {
        if (staffGateway.emailUsed(form.email())) return Results.failure(new StaffInteractError.EmailUsed());
        if (staffGateway.phoneUsed(form.phone())) return Results.failure(new StaffInteractError.PhoneUsed());

        Clinic clinic = createClinic(form);
        Staff staff = new StaffBuilder()
                .setFirstname(form.firstname())
                .setLastname(form.lastname())
                .setPatronymic(form.patronymic())

                .setBirthday(form.birthday())
                .setAvatarUrl(form.avatarUrl())

                .setEmail(form.email())
                .setPhone(form.phone())

                .createStaff();

        if (!staff.updatePassword(form.password(), form.repeatPassword()))
            return Results.failure(new StaffInteractError.PasswordNotMatch());

        if (clinic != null) {
            clinic = clinicGateway.createClinic(clinic);
            staff = new PersonalManager(clinic).addNewStaff(staff, StaffRole.Owner());
        }

        staffGateway.createStaff(staff);

        var messageBuilder = new RegistrationMessageBuilder()
                .setUsername(staff.getFullName())
                .setToken(staffGateway.generateToken(staff.getId()));

        if (clinic != null) {
            messageBuilder.setClinicName(clinic.getName());
        }

        staffGateway.sendEmail(staff.getEmail(), messageBuilder.build());

        return Results.success(staff);
    }

    private Clinic createClinic(ICreateStaffForm form) {
        if (form.clinicName() == null) return null;
        if (form.clinicAddress() == null) return null;

        return new ClinicBuilder()
                .setName(form.clinicName())
                .setAddress(form.clinicAddress())
                .setAvatarUrl(form.clinicAvatarUrl())
                .createClinic();
    }
}

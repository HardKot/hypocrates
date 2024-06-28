package com.hypocrates.hypocrates.useCase.RegistrationStaff;

import com.hypocrates.hypocrates.domain.staff.IStaffGateway;
import com.hypocrates.hypocrates.domain.appUser.IUserGateway;
import com.hypocrates.hypocrates.domain.clinic.Clinic;
import com.hypocrates.hypocrates.domain.clinic.ClinicBuilder;
import com.hypocrates.hypocrates.domain.clinic.IClinicGateway;
import com.hypocrates.hypocrates.domain.clinic.PersonalManager;
import com.hypocrates.hypocrates.domain.staff.Staff;
import com.hypocrates.hypocrates.domain.staff.StaffRole;
import com.hypocrates.hypocrates.useCase.StaffInteractError;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class RegistrationStaffUseCase {
    private IStaffGateway staffGateway;
    private IUserGateway userGateway;
    private IClinicGateway clinicGateway;
    private PersonalManager personalManager;

    public Result<Staff, StaffInteractError> execute(ICreateStaffForm form) {
        if (userGateway.emailUsed(form.email())) return Results.failure(new StaffInteractError.EmailUsed());
        if (userGateway.phoneUsed(form.phone())) return Results.failure(new StaffInteractError.PhoneUsed());

        var clinic = createClinic(form);
        var ownerRole = personalManager(clinic).ownerRole();
        var staff = staffGateway.mapToFrom(form);

        if (!staff.updatePassword(form.password(), form.repeatPassword()))
            return Results.failure(new StaffInteractError.PasswordNotMatch());

        if (clinic != null) {
            clinic = clinicGateway.createClinic(clinic);
            staff = personalManager(clinic).addNewStaff(staff, ownerRole);
        }

        staffGateway.createStaff(staff);

        var messageBuilder = new RegistrationMessageBuilder()
                .username(staff.getFullName())
                .token(staffGateway.generateToken(staff.getId()));

        if (clinic != null) {
            messageBuilder.clinicName(clinic.getName());
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



    private PersonalManager personalManager(Clinic clinic) {
        if (this.personalManager != null) return this.personalManager;
        return new PersonalManager(clinic, staffGateway);
    }
}

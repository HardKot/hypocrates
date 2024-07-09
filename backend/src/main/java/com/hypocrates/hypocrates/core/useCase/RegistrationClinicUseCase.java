package com.hypocrates.hypocrates.core.useCase;

import com.hypocrates.hypocrates.core.domain.appUser.IUserGateway;
import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import com.hypocrates.hypocrates.core.domain.clinic.IClinicGateway;
import com.hypocrates.hypocrates.core.domain.clinic.PersonalManager;
import com.hypocrates.hypocrates.core.domain.staff.IStaffGateway;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistrationClinicUseCase {
    private IClinicGateway clinicGateway;
    private IStaffGateway staffGateway;
    private IUserGateway userGateway;

    public Result<Clinic, ClinicInteractError> registerClinic(Form form) {
        if (clinicGateway.byName(form.name()) == null) return Results.failure(new ClinicInteractError.NameNoUniqueException());

        var clinic = clinicGateway.mapToCreateForm(form);

        var user = userGateway.mapToCreateForm(form);
        if (userGateway.emailUsed(user.getEmail())) {
            user = userGateway.getByEmail(user.getEmail());
        }

        var personalManager = new PersonalManager(clinic, staffGateway);

        var staff = personalManager.addNewStaff(user, personalManager.ownerRole());

        clinicGateway.createClinic(clinic);
        staffGateway.createStaff(staff);

        return Results.success(clinic);
    }


    public static interface Form {
        String firstname();
        String lastname();
        String patronymic();

        String email();

        String name();
        String address();
    }
}

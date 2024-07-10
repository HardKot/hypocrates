package com.hypocrates.hypocrates.core.useCase;

import com.hypocrates.hypocrates.core.domain.appUser.IUserGateway;
import com.hypocrates.hypocrates.core.domain.appUser.User;
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

    public Result<Clinic, ClinicInteractError> registerClinic() {
        var clinic = clinicGateway.getClinic();
        if (clinic != null) return Results.success(clinic);

        clinic = Clinic.builder()
                .codeID(clinicGateway.getRandomString())
                .build();

        var ownerEmail = clinicGateway.getEmailOwner();
        if (ownerEmail == null) return Results.failure(new ClinicInteractError.OwnerEmailNotFound());

        var owner = User.builder()
                .email(ownerEmail)
                .build();

        var personalManager = new PersonalManager(clinic, staffGateway);
        var staff = personalManager.addNewStaff(owner, personalManager.ownerRole());

        clinic = clinicGateway.saveClinic(clinic);
        staffGateway.saveStaff(staff);
        userGateway.saveUser(owner);


        var token = staffGateway.generateToken(owner.getId());

        staffGateway.sendEmail(ownerEmail, activateMessage(token));

        return Results.success(clinic);
    }


    private String activateMessage(String token) {
        return "<h4>Добро пожаловать в <b>Hypocrates</b>!</h4>\n" +
                "Для дальнейшей работы необходимо подтвердить email, перейдя по ссылке ниже:\n" +
                clinicGateway.getHostName() + "/activate/" + token;
    }
}

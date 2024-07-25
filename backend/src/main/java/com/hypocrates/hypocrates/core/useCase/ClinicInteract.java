package com.hypocrates.hypocrates.core.useCase;

import com.hypocrates.hypocrates.core.domain.appUser.IUserGateway;
import com.hypocrates.hypocrates.core.domain.appUser.User;
import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import com.hypocrates.hypocrates.core.domain.clinic.IClinicGateway;
import com.hypocrates.hypocrates.core.domain.clinic.PersonalManager;
import com.hypocrates.hypocrates.core.domain.staff.IStaffGateway;
import com.hypocrates.hypocrates.core.domain.staff.Staff;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClinicInteract {
    private IClinicGateway clinicGateway;
    private IStaffGateway staffGateway;
    private IUserGateway userGateway;


    public Result<Clinic, ClinicInteractError> registtationlinic(User user, Clinic clinic) {
        var personalManager = new PersonalManager(clinic, staffGateway);
        var owner = personalManager.addNewStaff(user, personalManager.ownerRole());

        staffGateway.saveStaff(owner);

        var token = staffGateway.generateToken(owner.getId());
        var activateLink = getActivateLink(token);
        staffGateway.sendEmailRegistration(owner.getEmail(), activateLink, owner);

        return Results.success(clinic);
    }

    public Result<Clinic, ClinicInteractError> activateClinic(Staff owner, Clinic clinic) {
        var token = staffGateway.generateToken(owner.getId());
        var activateLink = getActivateLink(token);
        staffGateway.sendEmailRegistration(owner.getEmail(), activateLink, owner);

        return Results.success(clinic);
    }

    private String getActivateLink(String token) {
        var activateLink = new StringBuilder();
        var hostName = clinicGateway.getHostName();
        if (hostName == null) hostName = "localhost";

        activateLink.append("http://");
        activateLink.append(hostName);
        activateLink.append("/activate/");
        activateLink.append(token.replace(".", "/"));
        return activateLink.toString();
    }
}
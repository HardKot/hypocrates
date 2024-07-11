package com.hypocrates.hypocrates.core.domain.staff;

import com.hypocrates.hypocrates.core.useCase.RegistrationStaff.ICreateStaffForm;

import java.util.UUID;

public interface IStaffGateway {
    void sendEmail(String email, String message);

    Staff createStaff(Staff staff);

    String generateToken(UUID userId);


    StaffRole getStaffRoleByName(String name);

    StaffRole createStaffRole(StaffRole role);

    Staff mapToFrom(ICreateStaffForm form);

    Staff saveStaff(Staff staff);


}

package com.hypocrates.hypocrates.domain.staff;

import com.hypocrates.hypocrates.domain.clinic.Clinic;
import com.hypocrates.hypocrates.useCase.RegistrationStaff.ICreateStaffForm;

public interface IStaffGateway {
    void sendEmail(String email, String message);

    Staff createStaff(Staff staff);

    String generateToken(Long userId);

    Staff createdFormToEntity(ICreateStaffForm form);

    StaffRole getStaffRoleByName(String name, Clinic clinic);

    StaffRole createStaffRole(StaffRole role);

    Staff mapToFrom(ICreateStaffForm form);
}

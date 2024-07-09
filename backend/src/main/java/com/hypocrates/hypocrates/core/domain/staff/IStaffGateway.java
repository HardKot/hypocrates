package com.hypocrates.hypocrates.core.domain.staff;

import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import com.hypocrates.hypocrates.core.useCase.RegistrationClinicUseCase;
import com.hypocrates.hypocrates.core.useCase.RegistrationStaff.ICreateStaffForm;

public interface IStaffGateway {
    void sendEmail(String email, String message);

    Staff createStaff(Staff staff);

    String generateToken(Long userId);

    Staff createdFormToEntity(ICreateStaffForm form);

    StaffRole getStaffRoleByName(String name, Clinic clinic);

    StaffRole createStaffRole(StaffRole role);

    Staff mapToFrom(ICreateStaffForm form);

    Staff mapToCreateForm(RegistrationClinicUseCase.Form form);
}

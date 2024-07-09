package com.hypocrates.hypocrates.core.domain.appUser;

import com.hypocrates.hypocrates.core.domain.staff.Staff;
import com.hypocrates.hypocrates.core.useCase.RegistrationClinicUseCase;
import com.hypocrates.hypocrates.core.useCase.RegistrationStaff.ICreateStaffForm;

public interface IUserGateway {
    boolean emailUsed(String email);
    boolean phoneUsed(String phone);
    User mapToUser(ICreateStaffForm form);

    User mapToCreateForm(RegistrationClinicUseCase.Form form);

    User getByEmail(String email);
}

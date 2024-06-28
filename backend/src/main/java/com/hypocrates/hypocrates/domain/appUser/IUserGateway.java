package com.hypocrates.hypocrates.domain.appUser;

import com.hypocrates.hypocrates.useCase.RegistrationStaff.ICreateStaffForm;

public interface IUserGateway {
    boolean emailUsed(String email);
    boolean phoneUsed(String phone);
    User mapToUser(ICreateStaffForm form);
}

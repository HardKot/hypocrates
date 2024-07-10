package com.hypocrates.hypocrates.core.useCase.RegistrationStaff;

import com.hypocrates.hypocrates.core.domain.clinic.IClinicGateway;
import com.hypocrates.hypocrates.core.domain.staff.IStaffGateway;
import com.hypocrates.hypocrates.core.domain.appUser.IUserGateway;
import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import com.hypocrates.hypocrates.core.domain.clinic.ClinicBuilder;
import com.hypocrates.hypocrates.core.domain.clinic.PersonalManager;
import com.hypocrates.hypocrates.core.domain.staff.Staff;
import com.hypocrates.hypocrates.core.useCase.StaffInteractError;
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
      return null;
    }

}

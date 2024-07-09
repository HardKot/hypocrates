package com.hypocrates.hypocrates.core.domain.clinic;

import com.hypocrates.hypocrates.core.useCase.RegistrationClinicUseCase;

public interface IClinicGateway {

    Clinic createClinic(Clinic clinic);

    Clinic byName(String clinicName);

    Clinic mapToCreateForm(RegistrationClinicUseCase.Form form);
}

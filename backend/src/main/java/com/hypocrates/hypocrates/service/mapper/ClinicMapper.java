package com.hypocrates.hypocrates.service.mapper;

import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import com.hypocrates.hypocrates.core.useCase.RegistrationClinicUseCase;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClinicMapper {

    Clinic createClinicFormToClinic(RegistrationClinicUseCase.Form form);
}

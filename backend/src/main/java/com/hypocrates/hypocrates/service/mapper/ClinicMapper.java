package com.hypocrates.hypocrates.service.mapper;

import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import com.hypocrates.hypocrates.core.useCase.RegistrationClinicUseCase;
import com.hypocrates.hypocrates.database.schema.ClinicSchema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClinicMapper {

    Clinic createClinicFormToClinic(RegistrationClinicUseCase.Form form);
}

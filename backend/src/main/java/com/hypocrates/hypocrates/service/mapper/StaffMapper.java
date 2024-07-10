package com.hypocrates.hypocrates.service.mapper;

import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import com.hypocrates.hypocrates.core.domain.staff.Staff;
import com.hypocrates.hypocrates.core.useCase.RegistrationClinicUseCase;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StaffMapper {
//
//    @Mapping(target = "updateAt", ignore = true)
//    @Mapping(target = "createAt", ignore = true)
//
//    @Mapping(target = "clinic.id", source = "clinicId")
//    @Mapping(target = "role.id", source = "roleId")
//    StaffSchema dtoToSchema(StaffDTO dto);
//
//    @Mapping(target = "clinicId", source = "clinic.id")
//    @Mapping(target = "roleId", source = "role.id")
//    StaffDTO schemaToDto(StaffSchema schema);
//
//    @Mapping(target = "clinic.id", source = "clinicId")
//    @Mapping(target = "role.id", source = "roleId")
//    Staff dtoToDomain(StaffDTO dto);
//
//    @Mapping(target = "clinicId", source = "clinic.id")
//    @Mapping(target = "roleId", source = "role.id")
//    StaffDTO domainToDto(Staff staff);
//
//    Staff createForm(ICreateStaffForm form);
}

package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.mapper;

import com.hypocrates.hypocrates.domain.adminManagement.interfaces.mapper.IClinicMapper;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.application.useCase.staffInteract.dto.IStaffRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { IClinicMapper.class, IStaffRoleMapper.class })
public interface IStaffMapper {
    Staff schemaToModel(Staff schema);

    Staff modelToSchema(Staff model);

    Staff formToSchema(IStaffRegistration form);
}

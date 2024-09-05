package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.mapper;

import com.hypocrates.hypocrates.application.dto.IInvitedStaffForm;
import com.hypocrates.hypocrates.application.dto.UpdateStaffForm;
import com.hypocrates.hypocrates.domain.adminManagement.interfaces.mapper.IClinicMapper;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { IClinicMapper.class, IStaffRoleMapper.class })
public interface IStaffMapper {
    Staff schemaToModel(Staff schema);

    Staff modelToSchema(Staff model);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "invitedStaff", ignore = true)
    @Mapping(target = "clinic", ignore = true)
    Staff formToSchema(IInvitedStaffForm form);

    Staff formToEntity(UpdateStaffForm form, Staff staff);
}

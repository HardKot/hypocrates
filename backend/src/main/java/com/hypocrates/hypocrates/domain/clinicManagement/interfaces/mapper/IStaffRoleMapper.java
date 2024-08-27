package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.mapper;

import com.hypocrates.hypocrates.domain.adminManagement.interfaces.mapper.IClinicMapper;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.StaffRole;
import com.hypocrates.hypocrates.configs.database.clinics.schema.StaffRoleSchema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {IClinicMapper.class})
public interface IStaffRoleMapper {
    StaffRoleSchema modelToSchema(StaffRole staffRoleModel);

    StaffRole schemaToModel(StaffRoleSchema staffRoleSchema);
}

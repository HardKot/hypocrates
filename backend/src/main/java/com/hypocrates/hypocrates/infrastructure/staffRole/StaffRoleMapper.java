package com.hypocrates.hypocrates.infrastructure.staffRole;

import com.hypocrates.hypocrates.entity.staff.StaffRoleModel;
import com.hypocrates.hypocrates.infrastructure.clinic.ClinicMapper;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.StaffRoleSchema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClinicMapper.class})
public interface StaffRoleMapper {
    StaffRoleSchema modelToSchema(StaffRoleModel staffRoleModel);

    StaffRoleModel schemaToModel(StaffRoleSchema staffRoleSchema);
}

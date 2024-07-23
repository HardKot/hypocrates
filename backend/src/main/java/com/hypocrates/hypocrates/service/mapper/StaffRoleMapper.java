package com.hypocrates.hypocrates.service.mapper;


import com.hypocrates.hypocrates.core.domain.staff.StaffRole;
import com.hypocrates.hypocrates.database.schema.StaffRoleSchema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StaffRoleMapper {

    @Mapping(target = "id", source = "staffRole.id")
    @Mapping(target = "name", source = "staffRole.name")
    @Mapping(target = "rules", source = "staffRole.rules")
    StaffRoleSchema toSchema(StaffRole staffRole, StaffRoleSchema staffRoleSchema);

    StaffRole toEntity(StaffRoleSchema staffRoleSchema);
}

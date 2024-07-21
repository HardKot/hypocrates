package com.hypocrates.hypocrates.service.mapper;


import com.hypocrates.hypocrates.core.domain.staff.StaffRole;
import com.hypocrates.hypocrates.database.schema.StaffRoleSchema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StaffRoleMapper {
//    StaffRoleSchema toSchema(StaffRole staffRole);
//    StaffRole toEntity(StaffRoleSchema staffRoleSchema);
}

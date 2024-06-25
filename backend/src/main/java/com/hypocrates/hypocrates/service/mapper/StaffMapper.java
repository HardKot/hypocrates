package com.hypocrates.hypocrates.service.mapper;

import com.hypocrates.hypocrates.database.schema.StaffSchema;
import com.hypocrates.hypocrates.domain.Staff;
import com.hypocrates.hypocrates.dto.StaffDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StaffMapper {
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);

    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "createAt", ignore = true)

    @Mapping(target = "clinic.id", source = "clinicId")
    @Mapping(target = "role.id", source = "roleId")
    StaffSchema dtoToSchema(StaffDTO dto);

    @Mapping(target = "clinicId", source = "clinic.id")
    @Mapping(target = "roleId", source = "role.id")
    StaffDTO schemaToDto(StaffSchema schema);

    @Mapping(target = "clinic.id", source = "clinicId")
    @Mapping(target = "role.id", source = "roleId")
    Staff dtoToDomain(StaffDTO dto);

    @Mapping(target = "clinicId", source = "clinic.id")
    @Mapping(target = "roleId", source = "role.id")
    StaffDTO domainToDto(Staff staff);
}

package com.hypocrates.hypocrates.service.mapper;

import com.hypocrates.hypocrates.core.domain.staff.Staff;
import com.hypocrates.hypocrates.database.schema.StaffSchema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


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

    @Mapping(target = "id", source = "staff.id")
    @Mapping(target = "firstname", source = "staff.firstname")
    @Mapping(target = "lastname", source = "staff.lastname")
    @Mapping(target = "patronymic", source = "staff.patronymic")
    @Mapping(target = "birthday", source = "staff.birthday")
    @Mapping(target = "avatarUrl", source = "staff.avatarUrl")
    @Mapping(target = "email", source = "staff.email")
    @Mapping(target = "emailIsActive", source = "staff.emailIsActive")
    @Mapping(target = "phone", source = "staff.phone")
    @Mapping(target = "phoneIsActive", source = "staff.phoneIsActive")
    @Mapping(target = "password", source = "staff.password")
    @Mapping(target = "role", source = "staff.role")
    @Mapping(target = "updateAt", source = "staffSchema.updateAt")
    @Mapping(target = "createAt", source = "staffSchema.createAt")
    StaffSchema toSchema(Staff staff, StaffSchema staffSchema);

    Staff toEntity(StaffSchema staffSchema);

}

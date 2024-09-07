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
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "invitedStaff", ignore = true)
//    @Mapping(target = "clinic", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    Staff formToSchema(IInvitedStaffForm form);

    @Mapping(target = "firstname", source = "form.firstname")
    @Mapping(target = "lastname", source = "form.lastname")
    @Mapping(target = "patronymic", source = "form.patronymic")
    @Mapping(target = "birthday", source = "form.birthday")
    @Mapping(target = "avatarUrl", source = "form.avatarUrl")
    @Mapping(target = "email", source = "form.email")
    @Mapping(target = "phone", source = "form.phone")
    @Mapping(target = "password", source = "staff.password")
    @Mapping(target = "invitedStaff", source = "staff.invitedStaff")
    Staff formToEntity(UpdateStaffForm form, Staff staff);
}

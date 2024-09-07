package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.mapper;

import com.hypocrates.hypocrates.domain.adminManagement.interfaces.mapper.IClinicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {IClinicMapper.class})
public interface IStaffRoleMapper {

}

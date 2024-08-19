package com.hypocrates.hypocrates.infrastructure.staff.service;

import com.hypocrates.hypocrates.entity.staff.StaffModel;
import com.hypocrates.hypocrates.infrastructure.clinic.ClinicMapper;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.infrastructure.staff.form.StaffRegistrationForm;
import com.hypocrates.hypocrates.infrastructure.staffRole.StaffRoleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ClinicMapper.class, StaffRoleMapper.class })
public interface StaffMapper {
    StaffModel schemaToModel(StaffSchema schema);

    StaffSchema modelToSchema(StaffModel model);

    StaffSchema formToSchema(StaffRegistrationForm form);

}

package com.hypocrates.hypocrates.infrastructure.clinic;

import com.hypocrates.hypocrates.entity.clinic.ClinicModel;
import com.hypocrates.hypocrates.infrastructure.config.database.admin.schema.ClinicSchema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClinicMapper {
    ClinicModel schemaToModel(ClinicSchema clinicSchema);

    ClinicSchema modelToSchema(ClinicModel clinicModel);
}

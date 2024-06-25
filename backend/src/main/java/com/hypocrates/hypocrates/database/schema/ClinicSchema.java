package com.hypocrates.hypocrates.database.schema;

import com.hypocrates.hypocrates.dto.ClinicDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table
public class ClinicSchema extends BaseSchema {
    private String name;
    private String address;
    private String avatarUrl;


    static ClinicSchema dtoToSchema(ClinicDTO dto) {
        ClinicSchema schema = new ClinicSchema();
        schema.setId(dto.getId());
        schema.setName(dto.getName());
        schema.setAddress(dto.getAddress());
        schema.setAvatarUrl(dto.getAvatarUrl());
        return schema;
    }
}

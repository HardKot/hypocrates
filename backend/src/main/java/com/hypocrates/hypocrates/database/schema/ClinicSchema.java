package com.hypocrates.hypocrates.database.schema;

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

}

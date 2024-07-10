package com.hypocrates.hypocrates.database.adminSchema;

import com.hypocrates.hypocrates.database.schema.BaseSchema;
import jakarta.persistence.Entity;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClinicSchema extends BaseSchema {
    private String name;
    private String address;
    private String avatarUrl;
    private String code;
}

package com.hypocrates.hypocrates.database.schema;

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
}

package com.hypocrates.hypocrates.database.schema;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClinicSchema extends BaseSchema {
    private String name;
    private String address;
    private String avatarUrl;
}

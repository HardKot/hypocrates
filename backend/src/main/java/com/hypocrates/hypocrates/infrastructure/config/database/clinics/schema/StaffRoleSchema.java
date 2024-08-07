package com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema;


import com.hypocrates.hypocrates.entity.staff.AppRule;
import com.hypocrates.hypocrates.infrastructure.config.database.BaseSchema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.Set;

@Entity(name = "StaffRole")
@Getter
@Setter
public class StaffRoleSchema extends BaseSchema {
    private String name;

    @ElementCollection(targetClass = AppRule.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "StaffRole_Rules", joinColumns = @JoinColumn(name = "staff_role_schema_id"))
    private Set<AppRule> rules;
}

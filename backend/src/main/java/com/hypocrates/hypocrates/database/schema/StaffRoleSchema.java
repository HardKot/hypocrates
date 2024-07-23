package com.hypocrates.hypocrates.database.schema;


import com.hypocrates.hypocrates.core.domain.staff.AppRule;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "staff_role_schema")
@NoArgsConstructor
public class StaffRoleSchema extends BaseSchema {
    private String name;

    @ElementCollection(targetClass = AppRule.class)
    @Enumerated(EnumType.STRING)
    private Set<AppRule> rules;

}

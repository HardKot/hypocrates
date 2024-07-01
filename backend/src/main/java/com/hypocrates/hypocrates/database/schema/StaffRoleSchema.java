package com.hypocrates.hypocrates.database.schema;


import com.hypocrates.hypocrates.domain.staff.AppRule;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StaffRoleSchema extends BaseSchema {
    private String name;

    @ElementCollection(targetClass = AppRule.class)
    @Enumerated(EnumType.STRING)
    private Set<AppRule> rules;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private ClinicSchema clinic;
}

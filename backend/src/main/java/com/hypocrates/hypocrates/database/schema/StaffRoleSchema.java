package com.hypocrates.hypocrates.database.schema;


import com.hypocrates.hypocrates.core.domain.staff.AppRule;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "staff_role_schema")
@NoArgsConstructor
public class StaffRoleSchema extends BaseSchema {
    private String name;

    @ElementCollection(targetClass = AppRule.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "Staff_Role_Schema_Rule", joinColumns = @JoinColumn(name = "staff_role_id"))
    @Enumerated(EnumType.STRING)
    private Set<AppRule> rules;

}

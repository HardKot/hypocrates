package com.hypocrates.hypocrates.core.domain.staff;

import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class StaffRole {
    private Long id;
    private String name;
    private Clinic clinic;

    private AppRule[] rules;

    static public StaffRoleBuilder OwnerBuilder() {
        return StaffRole.builder().name("Owner").rules(AppRule.values());
    }
}

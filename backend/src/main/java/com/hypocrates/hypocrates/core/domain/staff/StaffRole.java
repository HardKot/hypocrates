package com.hypocrates.hypocrates.core.domain.staff;

import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@Builder
@AllArgsConstructor
public class StaffRole {
    private UUID id;
    private String name;

    private AppRule[] rules;

    static public StaffRoleBuilder OwnerBuilder() {
        return StaffRole.builder().name("Owner").rules(AppRule.values());
    }
}

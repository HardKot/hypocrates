package com.hypocrates.hypocrates.entity.staff;

import com.hypocrates.hypocrates.entity.AbstractEntity;
import com.hypocrates.hypocrates.entity.clinic.ClinicModel;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class StaffRoleModel extends AbstractEntity {
    private String name;
    private AppRule[] rules;

    public static StaffRoleModel Owner() {
        return StaffRoleModel.builder()
                .name("Owner")
                .rules(AppRule.values())
                .build();
    }
}

package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import com.hypocrates.hypocrates._entities.staff.AppRule;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class StaffRole extends AbstractClinicEntity {
    private String name;
    private Set<AppRule> rules;

    public static StaffRole Owner() {
        return StaffRole.builder()
                .name("Owner")
                .rules(Set.of(AppRule.values()))
                .build();
    }
}

package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Staff extends AbstractUser {
    @ManyToOne(fetch = FetchType.EAGER)
    private StaffRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Staff invitedStaff;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    private Date dateBanned;

    public boolean isBanned() {
        if (dateBanned == null) {
            return true;
        }
        return dateBanned.before(new Date());
    }

    public boolean hasAccess(StaffRole.AppRule appRule) {
        Set<StaffRole.AppRule> rules = getRole().getRules();
        for (StaffRole.AppRule rule : rules) {
            if (rule.equals(appRule)) return true;
        }

        return false;
    }

    public static Staff Administrator(String email) {
        var role = StaffRole.Owner();

        return Staff.builder()
                .email(email)
                .role(role)
                .build();
    }
}

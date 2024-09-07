package com.hypocrates.hypocrates.domain.clinicManagement.entities;

//import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Staff")
@SuperBuilder
@NoArgsConstructor
public class Staff extends AbstractUser {
    @ManyToOne(fetch = FetchType.EAGER)
    private StaffRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invited_staff_id")
    private Staff invitedStaff;

    @Column(name = "global_staff_id")
    private String globalStaffId;

    @Column(name = "date_banned")
    private Date dateBanned;

    @Transient
    private Clinic clinic;

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

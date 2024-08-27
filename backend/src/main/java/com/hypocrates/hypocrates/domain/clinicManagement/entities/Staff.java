package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY)
    private List<Staff> securityToke;

    public static Staff Administrator(String email) {
        var role = StaffRole.Owner();

        return Staff.builder()
                .email(email)
                .role(role)
                .build();
    }
}

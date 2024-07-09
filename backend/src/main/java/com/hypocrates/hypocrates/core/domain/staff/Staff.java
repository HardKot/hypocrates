package com.hypocrates.hypocrates.core.domain.staff;

import com.hypocrates.hypocrates.core.domain.appUser.User;
import com.hypocrates.hypocrates.core.domain.clinic.Clinic;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Staff extends User {
    private Long id;

    private Clinic clinic;

    private StaffRole role;
}

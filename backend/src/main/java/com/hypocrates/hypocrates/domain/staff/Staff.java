package com.hypocrates.hypocrates.domain.staff;

import com.hypocrates.hypocrates.domain.appUser.User;
import com.hypocrates.hypocrates.domain.clinic.Clinic;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class Staff extends User {
    private Long id;

    private Clinic clinic;

    private StaffRole role;
}

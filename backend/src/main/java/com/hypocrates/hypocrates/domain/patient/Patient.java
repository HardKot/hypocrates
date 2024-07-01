package com.hypocrates.hypocrates.domain.patient;

import com.hypocrates.hypocrates.domain.appUser.User;
import com.hypocrates.hypocrates.domain.clinic.Clinic;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class Patient extends User {
    private Clinic clinic;

}

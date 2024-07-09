package com.hypocrates.hypocrates.core.domain.clinic;


import com.hypocrates.hypocrates.core.domain.patient.Patient;
import com.hypocrates.hypocrates.core.domain.staff.Staff;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class Clinic {
    private Long id;
    private String name;
    private String address;
    private String avatarUrl;

    private Set<Patient> patients;
    private Set<Staff> staffs;

    private Clinic parentClinic;
    private Set<Clinic> childClinics;

    public void addStaff(Staff staff) {
        staffs.add(staff);

    }
}

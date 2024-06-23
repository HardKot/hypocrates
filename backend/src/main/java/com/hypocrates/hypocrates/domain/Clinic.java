package com.hypocrates.hypocrates.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class Clinic {
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

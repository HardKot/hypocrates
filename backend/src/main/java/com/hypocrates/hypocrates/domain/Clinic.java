package com.hypocrates.hypocrates.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
public class Clinic {
    private Long id;
    private String name;
    private String address;
    private String avatarUrl;

    private Set<Patient> patients;
    private Set<Staff> staffs;

    private Clinic parentClinic;
    private Set<Clinic> childClinics;

    public Clinic(Long id, String name, String address, String avatarUrl, Set<Patient> patients, Set<Staff> staffs, Clinic parentClinic, Set<Clinic> childClinics) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.patients = patients;
        this.staffs = staffs;
        this.parentClinic = parentClinic;
        this.childClinics = childClinics;
    }

    public void addStaff(Staff staff) {
        staffs.add(staff);

    }
}

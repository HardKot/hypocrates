package com.hypocrates.hypocrates.domain;

import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Accessors(chain = true) @Setter
public class ClinicBuilder {
    private Long id;
    private String name;
    private String address;
    private String avatarUrl;
    private Set<Patient> patients = Set.of();
    private Set<Staff> staff = Set.of();
    private Clinic parentClinic;
    private Set<Clinic> childClinics = Set.of();

    public Clinic createClinic() {
        return new Clinic(id, name, address, avatarUrl, patients, staff, parentClinic, childClinics);
    }
}

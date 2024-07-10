package com.hypocrates.hypocrates.core.domain.clinic;

import com.hypocrates.hypocrates.core.domain.patient.Patient;
import com.hypocrates.hypocrates.core.domain.staff.Staff;
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

}

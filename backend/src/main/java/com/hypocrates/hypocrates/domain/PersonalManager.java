package com.hypocrates.hypocrates.domain;

import lombok.Setter;

@Setter
public class PersonalManager {
    private Clinic clinic;

    public PersonalManager(Clinic clinic) {
        this.clinic = clinic;
    }

    public Staff addNewStaff(User user, StaffRole role) {
        var staff = new StaffBuilder()
                .setUser(user)
                .setClinic(clinic)
                .setRole(role)
                .createStaff();

        clinic.addStaff(staff);

        return staff;
    }
}

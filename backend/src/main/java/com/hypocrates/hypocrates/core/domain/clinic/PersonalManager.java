package com.hypocrates.hypocrates.core.domain.clinic;

import com.hypocrates.hypocrates.core.domain.appUser.User;
import com.hypocrates.hypocrates.core.domain.staff.Staff;
import com.hypocrates.hypocrates.core.domain.staff.IStaffGateway;
import com.hypocrates.hypocrates.core.domain.staff.StaffRole;
import lombok.Setter;

@Setter
public class PersonalManager {
    private Clinic clinic;
    private IStaffGateway staffGateway;

    public PersonalManager(Clinic clinic, IStaffGateway staffGateway) {
        this.clinic = clinic;
        this.staffGateway = staffGateway;
    }

    public Staff addNewStaff(User user, StaffRole role) {
        var staff = Staff.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .patronymic(user.getPatronymic())
                .birthday(user.getBirthday())
                .avatarUrl(user.getAvatarUrl())
                .email(user.getEmail())

                .password(user.getPassword())
                .phone(user.getPhone())

                .role(role)

                .build();

        clinic.addStaff(staff);
        return staff;
    }

    public StaffRole ownerRole() {
        var staffRole = staffGateway.getStaffRoleByName("Owner");

        if (staffRole == null) {
            staffRole = staffGateway.createStaffRole(StaffRole.OwnerBuilder().build());
        }

        return staffRole;
    }
}

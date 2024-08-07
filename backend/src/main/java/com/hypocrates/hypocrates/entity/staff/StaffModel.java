package com.hypocrates.hypocrates.entity.staff;

import com.hypocrates.hypocrates.entity.clinic.ClinicModel;
import com.hypocrates.hypocrates.entity.user.UserModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class StaffModel extends UserModel {

    private StaffRoleModel role;



    public static StaffModel Administrator(String email) {
        var role = StaffRoleModel.Owner();

        return StaffModel.builder()
                .email(email)
                .role(role)
                .build();
    }
}

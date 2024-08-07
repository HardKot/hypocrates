package com.hypocrates.hypocrates.entity.patient;

import com.hypocrates.hypocrates.entity.user.UserModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Patient extends UserModel {
    private String policyNumber;

}

package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Patient extends AbstractUser {
    public enum Gender {
        Male, Female, Other
    }

    @Column(name = "global_patient_id")
    private String globalPatientId;

    @Column(name = "policy_number")
    private String policyNumber;

    private Gender gender;
}

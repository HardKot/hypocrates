package com.hypocrates.hypocrates.domain.clinicManagement.entities;

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
    private String policyNumber;

}
package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class MedicalCard extends AbstractClinicEntity {
    private String number;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}

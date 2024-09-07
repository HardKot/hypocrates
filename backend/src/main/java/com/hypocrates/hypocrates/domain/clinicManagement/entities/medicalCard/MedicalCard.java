package com.hypocrates.hypocrates.domain.clinicManagement.entities.medicalCard;

import com.hypocrates.hypocrates.domain.clinicManagement.entities.AbstractClinicEntity;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Patient;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.*;

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

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

}

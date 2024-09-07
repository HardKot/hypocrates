package com.hypocrates.hypocrates.domain.clinicManagement.entities.medicalCard;

import com.hypocrates.hypocrates.domain.clinicManagement.entities.AbstractClinicEntity;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class MedicalProtocol extends AbstractClinicEntity {
    @ManyToOne
    private Staff staff;
}
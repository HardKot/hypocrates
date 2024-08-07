package com.hypocrates.hypocrates.entity.medicalCard;

import com.hypocrates.hypocrates.entity.AbstractEntity;
import com.hypocrates.hypocrates.entity.patient.Patient;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class MedicalCard extends AbstractEntity {
    private String number;

    private Patient patient;


}

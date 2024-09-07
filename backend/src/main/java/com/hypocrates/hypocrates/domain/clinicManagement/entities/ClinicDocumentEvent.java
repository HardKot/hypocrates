package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class ClinicDocumentEvent extends AbstractClinicEntity {
    @Column(name = "document_name")
    private String documentName;


}
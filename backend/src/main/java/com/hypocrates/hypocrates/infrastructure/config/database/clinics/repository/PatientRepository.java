package com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository;

import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.PatientSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<PatientSchema, UUID> {
}

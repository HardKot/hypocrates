package com.hypocrates.hypocrates.database.clinics.repository;

import com.hypocrates.hypocrates.database.clinics.schema.PatientSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<PatientSchema, UUID> {
}

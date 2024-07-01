package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.schema.PatientSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<PatientSchema, UUID> {
}

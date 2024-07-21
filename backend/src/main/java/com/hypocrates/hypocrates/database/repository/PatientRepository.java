package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.schema.PatientSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<PatientSchema, UUID> {
}

package com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository;

import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.ClinicConfigurationSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClinicConfigurationRepository extends JpaRepository<ClinicConfigurationSchema, UUID> {
    List<ClinicConfigurationSchema> findAll();
}
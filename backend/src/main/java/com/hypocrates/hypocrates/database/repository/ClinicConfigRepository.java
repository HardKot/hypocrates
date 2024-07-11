package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.schema.ClinicConfigSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicConfigRepository extends JpaRepository<ClinicConfigSchema, Integer> {
    Optional<ClinicConfigSchema> findByName(String name);

}

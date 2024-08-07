package com.hypocrates.hypocrates.infrastructure.config.database.admin.repository;

import com.hypocrates.hypocrates.infrastructure.config.database.admin.schema.ClinicSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClinicSchemaRepository extends JpaRepository<ClinicSchema, UUID> {
    Optional<ClinicSchema> findByEmail(String email);
    boolean existsByEmail(String email);

    boolean existsByCode(String code);

    void removeById(UUID id);

    @Override
    List<ClinicSchema> findAll();
}
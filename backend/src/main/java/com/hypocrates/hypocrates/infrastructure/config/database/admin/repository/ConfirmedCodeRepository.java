package com.hypocrates.hypocrates.infrastructure.config.database.admin.repository;

import com.hypocrates.hypocrates.infrastructure.config.database.admin.schema.ConfirmedCodeSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConfirmedCodeRepository extends JpaRepository<ConfirmedCodeSchema, UUID> {
    Optional<ConfirmedCodeSchema> findById(UUID uuid);

    void deleteById(UUID codeId);
}

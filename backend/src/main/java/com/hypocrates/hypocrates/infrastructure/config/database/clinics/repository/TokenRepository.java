package com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository;

import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.TokenSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<TokenSchema, UUID> {
    Optional<TokenSchema> findByToken(String token);
}

package com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository;

import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.TokenSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenSchema, Integer> {
    Optional<TokenSchema> findByToken(String token);
}
package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository;

import com.hypocrates.hypocrates.configs.database.clinics.schema.TokenSchema;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.SecurityToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ISecurityTokenRepository extends JpaRepository<SecurityToken, UUID> {
    Optional<TokenSchema> findByToken(String token);
}

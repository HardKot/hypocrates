package com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository;

import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.StaffSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<StaffSchema, UUID> {
    Optional<StaffSchema> findByEmail(String email);
    Optional<StaffSchema> findAllByRole_Name(String staffRoleName);
    boolean existsByEmail(String email);
}

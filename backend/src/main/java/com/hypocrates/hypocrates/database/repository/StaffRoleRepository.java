package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.schema.ClinicSchema;
import com.hypocrates.hypocrates.database.schema.StaffRoleSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StaffRoleRepository extends JpaRepository<StaffRoleSchema, UUID> {
    Optional<StaffRoleSchema> findByNameAndClinic(String name, ClinicSchema clinic);
}

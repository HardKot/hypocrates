package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.adminSchema.ClinicSchema;
import com.hypocrates.hypocrates.database.schema.StaffSchema;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface StaffRepository extends JpaRepository<StaffSchema, UUID> {
    Optional<StaffSchema> findByEmail(String email);
}

package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.adminSchema.ClinicSchema;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface ClinicRepository extends JpaRepository<ClinicSchema, UUID> {
}

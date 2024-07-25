package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.schema.StaffRoleSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StaffRoleRepository extends JpaRepository<StaffRoleSchema, UUID> {
    Optional<StaffRoleSchema> findByName(String name);
}

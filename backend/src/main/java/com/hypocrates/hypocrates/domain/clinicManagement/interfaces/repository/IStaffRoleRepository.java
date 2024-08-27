package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository;

import com.hypocrates.hypocrates.configs.database.clinics.schema.StaffRoleSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IStaffRoleRepository extends JpaRepository<StaffRoleSchema, UUID> {
    Optional<StaffRoleSchema> findByName(String name);
}

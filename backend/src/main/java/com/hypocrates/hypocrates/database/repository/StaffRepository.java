package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.schema.StaffSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<StaffSchema, UUID> {
    Optional<StaffSchema> findByEmail(String email);
}

package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository;

import com.hypocrates.hypocrates.domain.clinicManagement.entities.StaffRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IStaffRoleRepository extends JpaRepository<StaffRole, Long> {
    Optional<StaffRole> findByName(String name);
}

package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository;

import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IStaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByEmail(String email);

    Optional<Staff> findAllByRole_Name(String staffRoleName);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}

package com.hypocrates.hypocrates.domain.adminManagement.interfaces.repository;

import com.hypocrates.hypocrates.configs.database.admin.schema.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IClinicRepository extends JpaRepository<Clinic, UUID> {
    Optional<Clinic> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByCode(String code);

    void removeById(UUID id);

    @Override
    List<Clinic> findAll();
}
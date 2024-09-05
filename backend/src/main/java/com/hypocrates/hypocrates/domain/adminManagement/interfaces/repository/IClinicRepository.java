package com.hypocrates.hypocrates.domain.adminManagement.interfaces.repository;

import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IClinicRepository extends JpaRepository<Clinic, UUID> {
    Optional<Clinic> findByEmail(String email);

    Optional<Clinic> findByCode(String code);

    boolean existsByEmail(String email);

    boolean existsByCode(String code);

    void removeById(UUID id);

    @Override
    List<Clinic> findAll();
}
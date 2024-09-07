package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository;

import com.hypocrates.hypocrates.domain.clinicManagement.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {
}

package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository;

import com.hypocrates.hypocrates.domain.clinicManagement.entities.ClinicRowConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IClinicRowConfigurationRepository extends JpaRepository<ClinicRowConfiguration, Long> {
//    Iterable<ClinicRowConfiguration> getAll();
}
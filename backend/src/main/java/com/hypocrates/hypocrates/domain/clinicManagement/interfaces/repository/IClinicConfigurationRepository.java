package com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository;

import com.hypocrates.hypocrates.domain.clinicManagement.entities.ClinicConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClinicConfigurationRepository extends JpaRepository<ClinicConfiguration, Long> {
}
package com.hypocrates.hypocrates.application.services;

import com.hypocrates.hypocrates.infrastructure.staff.form.CreateStaffForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.hypocrates.hypocrates.configs.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.IStaffRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final IStaffRepository staffRepository;

  public StaffSchema createStaff(@Valid CreateStaffForm form) {
    return null;
  }
}

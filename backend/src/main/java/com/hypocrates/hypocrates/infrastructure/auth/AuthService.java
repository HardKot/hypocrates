package com.hypocrates.hypocrates.infrastructure.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.hypocrates.hypocrates.infrastructure.auth.dto.StaffRegistration;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository.StaffRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final StaffRepository staffRepository;

  public StaffSchema createStaff(StaffRegistration form) {
    return null;
  }
}

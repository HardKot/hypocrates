package com.hypocrates.hypocrates.infrastructure.staff.service;

import com.hypocrates.hypocrates.infrastructure.common.ConfirmedService;
import com.hypocrates.hypocrates.infrastructure.config.database.admin.repository.ConfirmedCodeRepository;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository.StaffRepository;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.infrastructure.config.exception.NotFoundSchema;
import com.hypocrates.hypocrates.infrastructure.staff.form.CreateStaffForm;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final ConfirmedCodeRepository confirmedCodeRepository;
    private final ConfirmedService confirmedService;

    public StaffSchema save(StaffSchema staffSchema) {
        if (staffSchema.getId() == null)
            throw new NotFoundSchema("Сотрудник с данным id не найден");
        return staffRepository.save(staffSchema);
    }

    public StaffSchema createStaff(@Valid CreateStaffForm form) {
        if (staffRepository.existsByEmail(staffSchema.getEmail())) {
            throw new RuntimeException("Сотрудник с таким email уже существует");
        }

        return staffRepository.save(staffSchema);
    }

    public StaffSchema findByEmail(String email) {
        return staffRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Сотрудник не найден"));
    }

    public StaffSchema confirmedEmail(UUID confirmedId, String code) {
        var uid = confirmedService.getEntityId(confirmedId, code);
        var staff = staffRepository.findById(uid)
                .orElseThrow(() -> new NotFoundSchema("Сотрудник с данным id не найден"));

        staff.setEmailIsActive(true);
        staffRepository.save(staff);

        return staff;
    }

}

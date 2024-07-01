package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.database.repository.StaffRoleRepository;
import com.hypocrates.hypocrates.database.schema.ClinicSchema;
import com.hypocrates.hypocrates.database.schema.StaffRoleSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffRoleService {
    private StaffRoleRepository staffRoleRepository;

    public StaffRoleSchema getStaffRoleByName(String name, ClinicSchema clinicSchema) {
        return staffRoleRepository.findByNameAndClinic(name, clinicSchema).orElse(null);
    }

    public StaffRoleSchema createStaffRole(StaffRoleSchema staffRoleSchema) {
        return staffRoleRepository.save(staffRoleSchema);
    }
}

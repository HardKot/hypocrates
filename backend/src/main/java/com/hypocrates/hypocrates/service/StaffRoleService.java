package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.database.repository.StaffRoleRepository;
import com.hypocrates.hypocrates.database.schema.StaffRoleSchema;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StaffRoleService {
    private StaffRoleRepository staffRoleRepository;

    public StaffRoleSchema getStaffRoleByName(String name) {
        return staffRoleRepository.findByName(name).orElse(null);
    }

    public StaffRoleSchema createStaffRole(StaffRoleSchema staffRoleSchema) {
        return staffRoleRepository.save(staffRoleSchema);
    }
}

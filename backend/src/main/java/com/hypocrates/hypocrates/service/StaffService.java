package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.context.ClinicContext;
import com.hypocrates.hypocrates.database.repository.StaffRepository;
import com.hypocrates.hypocrates.database.schema.StaffSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffService {
    private StaffRepository staffRepository;


    public StaffSchema createStaff(StaffSchema staffSchema) {
        return staffRepository.save(staffSchema);
    }

    public StaffSchema findByEmail(String email) {
        return staffRepository.findByEmail(email).orElse(null);
    }
}

package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.database.clinics.repository.StaffRepository;
import com.hypocrates.hypocrates.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.service.mapper.StaffMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StaffService {
    private StaffRepository staffRepository;
    private StaffMapper staffMapper;

    public StaffSchema createStaff(StaffSchema staffSchema) {
        return staffRepository.save(staffSchema);
    }

    public StaffSchema findByEmail(String email) {
        return staffRepository.findByEmail(email).orElse(null);
    }


    public StaffSchema getOwner() {
        return staffRepository.findAllByRole_Name("Owner").orElse(null);
    }
}

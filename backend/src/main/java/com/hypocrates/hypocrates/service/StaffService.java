package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.database.repository.StaffRepository;
import com.hypocrates.hypocrates.database.schema.StaffSchema;
import com.hypocrates.hypocrates.dto.StaffDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StaffService {
    private StaffRepository staffRepository;


    public void createStaff(StaffDTO dto) {

    }
}

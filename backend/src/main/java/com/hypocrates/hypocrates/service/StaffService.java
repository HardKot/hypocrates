package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.database.repository.StaffRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Service
public class StaffService {
    private StaffRepository staffRepository;


}

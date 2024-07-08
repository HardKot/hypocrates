package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.context.ClinicContext;
import com.hypocrates.hypocrates.database.repository.ClinicRepository;
import com.hypocrates.hypocrates.database.schema.ClinicSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClinicService {
    private ClinicRepository clinicRepository;

    public ClinicSchema create(ClinicSchema clinicSchema) {
        return clinicRepository.save(clinicSchema);
    }

    public ClinicSchema getById(UUID id) {
        return clinicRepository.findById(id).orElseGet(null);
    }


    public ClinicSchema getCurrentClinic() {
        return ClinicContext.get();
    }
}

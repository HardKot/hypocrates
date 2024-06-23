package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.database.repository.ClinicRepository;
import com.hypocrates.hypocrates.database.schema.ClinicSchema;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Setter
public class ClinicService {
    @Autowired
    private ClinicRepository clinicRepository;

    public Mono<ClinicSchema> create(ClinicSchema clinicSchema) {
        return clinicRepository.save(clinicSchema);
    }

    public Mono<ClinicSchema> getById(Long id) {
        return clinicRepository.findById(id);
    }
}

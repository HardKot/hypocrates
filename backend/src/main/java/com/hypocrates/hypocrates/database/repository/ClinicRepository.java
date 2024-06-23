package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.schema.ClinicSchema;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClinicRepository extends ReactiveCrudRepository<ClinicSchema, Long> {
}

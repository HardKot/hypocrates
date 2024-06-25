package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.schema.PatientSchema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PatientRepository extends CrudRepository<PatientSchema, Long> {
}

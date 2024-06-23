package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.schema.StaffSchema;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StaffRepository extends ReactiveCrudRepository<StaffSchema, Long> {
}

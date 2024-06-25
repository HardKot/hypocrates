package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.schema.StaffSchema;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<StaffSchema, Long> {
}

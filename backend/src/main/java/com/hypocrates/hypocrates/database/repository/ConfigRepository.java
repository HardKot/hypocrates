package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.schema.ConfigSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<ConfigSchema, Integer> {
    ConfigSchema findByName(String name);

}

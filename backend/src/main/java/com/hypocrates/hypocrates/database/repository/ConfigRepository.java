package com.hypocrates.hypocrates.database.repository;

import com.hypocrates.hypocrates.database.schema.ConfigSchema;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConfigRepository extends JpaRepository<ConfigSchema, Integer> {
    ConfigSchema findByName(String name);

}

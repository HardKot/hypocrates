package com.hypocrates.hypocrates.database;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrationConfig {
    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {

            flyway.migrate();
        };
    }
}
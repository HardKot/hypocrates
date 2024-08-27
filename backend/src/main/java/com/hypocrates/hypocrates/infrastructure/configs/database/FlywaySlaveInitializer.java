package com.hypocrates.hypocrates.infrastructure.configs.database;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywaySlaveInitializer {
    private final DataSource adminDataSource;

    public FlywaySlaveInitializer(
            @Qualifier("adminDataSource") DataSource adminDataSource
            ) {
        this.adminDataSource = adminDataSource;
    }

    @PostConstruct
    public void runAdminMigration() {
        Flyway adminFlyway = Flyway.configure()
                .locations("db/migration/admin")
                .dataSource(adminDataSource)
                .load();
        adminFlyway.baseline();
        adminFlyway.migrate();

    }

}

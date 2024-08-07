package com.hypocrates.hypocrates.infrastructure.config.database;

import com.hypocrates.hypocrates.infrastructure.config.database.admin.repository.ClinicSchemaRepository;
import com.hypocrates.hypocrates.infrastructure.config.database.admin.schema.ClinicSchema;
import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.function.Function;

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

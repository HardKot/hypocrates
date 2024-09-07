package com.hypocrates.hypocrates.infrastructure.configs.database.clinics;

import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import com.hypocrates.hypocrates.domain.adminManagement.interfaces.repository.IClinicRepository;
import com.hypocrates.hypocrates.infrastructure.context.ClinicContext;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;


@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "clinicEntityManagerFactory",
        transactionManagerRef = "clinicTransactionManager",
        basePackages = {"com.hypocrates.hypocrates.domain.clinicManagement"}
)
@Configuration
public class ClinicDatasourceConfiguration {
    private final Map<Object, Object> dataSources = new HashMap<>();
    private final IClinicRepository clinicSchemaRepository;
    private final ClinicRoutingDataSource clinicRoutingDataSource = new ClinicRoutingDataSource();

    public ClinicDatasourceConfiguration(@Lazy IClinicRepository clinicSchemaRepository) {
        this.clinicSchemaRepository = clinicSchemaRepository;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void startMigration(
    ) {
        Function<String, DataSource> createDataSource = clinicBuildDataSource(clinicDataSourceProperties());

        DataSource clinicDataSource = createDataSource.apply("demo");
        dataSources.put("demo", clinicDataSource);
        migrateClinicDatabase(clinicDataSource);

        List<Clinic> clinics = clinicSchemaRepository.findAll();

        for (Clinic clinicSchema : clinics) {
            String clinicCode = clinicSchema.getCode();
            clinicDataSource = createDataSource.apply(clinicCode);

            dataSources.put(clinicCode, clinicDataSource);
            migrateClinicDatabase(clinicDataSource);
        }
    }

    @Primary
    @Bean(name = "clinicDataSourceProperty")
    @ConfigurationProperties("clinic.datasource")
    public DataSourceProperties clinicDataSourceProperties() {
       return new DataSourceProperties();
    }

    @Bean(name = "clinicBuildDataSource")
    public Function<String, DataSource> clinicBuildDataSource(
            @Qualifier("clinicDataSourceProperty") DataSourceProperties dataSourceProperties) {
        return clinicCode -> {
            String url = dataSourceProperties.getUrl().replace("<clinic_code>", clinicCode);

            return dataSourceProperties
                    .initializeDataSourceBuilder()
                    .url(url)
                    .build();
        };
    }

    @Primary
    @Bean(name = "clinicDataSource")
    public DataSource clinicDataSource(
            @Qualifier("clinicBuildDataSource") Function<String, DataSource> createDataSource
    ) {
        dataSources.put("demo", createDataSource.apply("demo"));

        clinicRoutingDataSource.setTargetDataSources(dataSources);
        clinicRoutingDataSource.setDefaultTargetDataSource(createDataSource.apply("demo"));

        return clinicRoutingDataSource;
    }

    @Primary
    @Bean(name = "createClinicDatabase")
    public Function<Clinic, Void> createClinicDatabase(
            @Qualifier("adminDataSource") DataSource adminDataSource,
            @Qualifier("clinicBuildDataSource") Function<String, DataSource> createDataSource
    ) throws SQLException {
        var con = adminDataSource.getConnection();
        var stmt = con.createStatement();

        return clinic -> {
            try {
                stmt.execute("CREATE DATABASE app_clinic_%s;".formatted(clinic.getCode()));

                DataSource dataSource = createDataSource.apply(clinic.getCode());
                dataSources.put(clinic.getCode(), dataSource);
                clinicRoutingDataSource.initialize();
                migrateClinicDatabase(dataSource);
                ClinicContext.setClinic(clinic);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        };
    }

    private void migrateClinicDatabase(DataSource clinicDataSource) {
        Flyway clinicFlyway = Flyway.configure()
                .locations("db/migration/clinics")
                .dataSource(clinicDataSource)
                .load();
        clinicFlyway.baseline();
        clinicFlyway.migrate();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean clinicEntityManagerFactory(
            @Qualifier("clinicDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.hypocrates.hypocrates.domain.clinicManagement")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager clinicTransactionManager(
            @Qualifier("clinicEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}

package com.hypocrates.hypocrates.infrastructure.configs.database.clinics;

import com.hypocrates.hypocrates.domain.adminManagement.entities.Clinic;
import com.hypocrates.hypocrates.domain.adminManagement.interfaces.repository.IClinicRepository;
import com.hypocrates.hypocrates.infrastructure.context.ClinicContext;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    @Value("${spring.jpa.show-sql}")
    private Boolean sqlShow;
    private final Map<Object, Object> dataSources = new HashMap<>();
    private final IClinicRepository clinicSchemaRepository;
    private final ClinicRoutingDataSource clinicRoutingDataSource = new ClinicRoutingDataSource();

    public ClinicDatasourceConfiguration(IClinicRepository clinicSchemaRepository) {
        this.clinicSchemaRepository = clinicSchemaRepository;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void startMigration() {
        DataSource clinicDataSource = clinicBuildDataSource("demo");
        dataSources.put("demo", clinicDataSource);
        migrateClinicDatabase(clinicDataSource);

        List<Clinic> clinics = clinicSchemaRepository.findAll();

        for (Clinic clinicSchema : clinics) {
            String clinicCode = clinicSchema.getCode();
            clinicDataSource = clinicBuildDataSource(clinicCode);

            dataSources.put(clinicCode, clinicDataSource);
            migrateClinicDatabase(clinicDataSource);
        }
    }

    @Bean(name = "clinicDataSourceProperty")
    @ConfigurationProperties("spring.datasource.clinic")
    public DataSourceProperties clinicDataSourceProperties() {
       return new DataSourceProperties();
    }

    private DataSource clinicBuildDataSource(String clinicCode){
        DataSourceProperties dataSourceProperties = clinicDataSourceProperties();
        String url = dataSourceProperties.getUrl().replace("<clinic_code>", clinicCode);

        return dataSourceProperties
                .initializeDataSourceBuilder()
                .url(url)
                .build();
    }

    @Bean(name = "clinicDataSource")
    public DataSource clinicDataSource() {
        dataSources.put("demo", clinicBuildDataSource("demo"));

        clinicRoutingDataSource.setTargetDataSources(dataSources);
        clinicRoutingDataSource.setDefaultTargetDataSource(clinicBuildDataSource("demo"));

        return clinicRoutingDataSource;
    }

    @Bean(name = "createClinicDatabase")
    public Function<String, Void> createClinicDatabase(
            @Qualifier("adminDataSource") DataSource adminDataSource
    ) throws SQLException {
        var con = adminDataSource.getConnection();
        var stmt = con.createStatement();

        return clinicCode -> {
            try {
                stmt.execute("CREATE DATABASE app_clinic_%s;".formatted(clinicCode));

                DataSource dataSource = clinicBuildDataSource(clinicCode);
                dataSources.put(clinicCode, dataSource);
                clinicRoutingDataSource.initialize();
                migrateClinicDatabase(dataSource);
                ClinicContext.setClinicCode(clinicCode);
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
    public PlatformTransactionManager clinicTransactionManager(
            @Qualifier("clinicEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}

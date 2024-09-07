package com.hypocrates.hypocrates.infrastructure.configs.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "adminEntityManagerFactory",
        transactionManagerRef = "adminTransactionManager",
        basePackages = {"com.hypocrates.hypocrates.domain.medicalSharedData"}
)
public class MedicalSharedDataDatasourceConfiguration {
    @Bean
    @ConfigurationProperties("medical.datasource")
    public DataSourceProperties medicalSharedDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource medicalSharedDataSource() {
        return medicalSharedDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean medicalSharedEntityManagerFactory(
            @Qualifier("medicalSharedDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {

        return builder
                .dataSource(dataSource)
                .packages("com.hypocrates.hypocrates.domain.medicalSharedData")
                .build();
    }

    @Bean
    public PlatformTransactionManager medicalSharedTransactionManager(
            @Qualifier("medicalSharedEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory
    ) {

        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}

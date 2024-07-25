package com.hypocrates.hypocrates.database;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class ClinicDatasourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.clinic")
    public DataSourceProperties clinicDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.clinic.hikari")
    public DataSource clinicDataSource() {
        return clinicDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}

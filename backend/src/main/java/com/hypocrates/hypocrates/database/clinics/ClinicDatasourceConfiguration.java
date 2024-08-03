package com.hypocrates.hypocrates.database.clinics;

import com.hypocrates.hypocrates.context.ClinicContext;
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
    public DataSource clinicDataSource(ClinicContext clinicContext) {
        return clinicDataSourceProperties()
                .initializeDataSourceBuilder()
                .url(clinicDataSourceProperties().getUrl().replace("<clinic_code>", clinicContext.getClinicCode()))
                .build();
    }
}

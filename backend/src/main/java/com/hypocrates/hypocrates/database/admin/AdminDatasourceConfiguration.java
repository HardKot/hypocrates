package com.hypocrates.hypocrates.database.admin;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AdminDatasourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.admin")
    public DataSourceProperties adminDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource adminDataSource() {
        return adminDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}

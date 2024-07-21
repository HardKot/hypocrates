package com.hypocrates.hypocrates.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "clinicEntityManagerFactory",
        transactionManagerRef = "clinicTransactionManager"
)
public class ClinicJpaConfiguration {
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean clinicEntityManagerFactory(
            @Qualifier("clinicDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(dataSource)
                .packages(new String[] { "com.hypocrates.hypocrates.database" })
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

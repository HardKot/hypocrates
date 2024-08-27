package com.hypocrates.hypocrates.infrastructure.configs.database.admin;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
        basePackages = {"com.hypocrates.hypocrates.domain.adminManagement"}
)
public class AdminJpaConfiguration {
    @Value("${spring.jpa.show-sql}")
    private Boolean sqlShow;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean adminEntityManagerFactory(
            @Qualifier("adminDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {

        return builder
                .dataSource(dataSource)
                .packages("com.hypocrates.hypocrates.domain.adminManagement")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager adminTransactionManager(
            @Qualifier("adminEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory
    ) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(sqlShow);

        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}

package com.hypocrates.hypocrates.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class R2DBCConfig {
    @Value("${spring.r2dbc.username}")
    private String username;

    @Value("${spring.r2dbc.password")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
          ConnectionFactoryOptions.builder()
                  .option(DRIVER, "postgresql")
                  .option(HOST, "localhost")
                  .option(PORT, 5432)
                  .option(USER, username)
                  .option(PASSWORD, password)
                  .option(DATABASE, "hypocrates")
                  .build()
        );
    }
}

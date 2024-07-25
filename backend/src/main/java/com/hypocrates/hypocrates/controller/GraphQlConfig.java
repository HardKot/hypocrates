package com.hypocrates.hypocrates.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQlConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer () {
        return wiringBuilder -> wiringBuilder.scalar(DateScalar.DATE);
    }
}

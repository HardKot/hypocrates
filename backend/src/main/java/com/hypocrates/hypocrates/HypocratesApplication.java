package com.hypocrates.hypocrates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@SpringBootApplication
public class HypocratesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HypocratesApplication.class, args);
	}
}

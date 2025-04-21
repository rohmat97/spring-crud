package com.example.postgrescrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class PostgresCrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(PostgresCrudApplication.class, args);
	}
}
package com.jonas.jonasbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class JonasbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(JonasbankApplication.class, args);
	}


}
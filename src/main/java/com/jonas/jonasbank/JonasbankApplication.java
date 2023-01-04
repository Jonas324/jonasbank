package com.jonas.jonasbank;

import com.jonas.jonasbank.user.User;
import com.jonas.jonasbank.user.UserRepository;
import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JonasbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(JonasbankApplication.class, args);
	}

	@Bean
	public CommandLineRunner usersMockup(UserRepository userRepository){
		return args -> {
			userRepository.save(new User("benny", "Carlsson", 0L));
		};
	}

}
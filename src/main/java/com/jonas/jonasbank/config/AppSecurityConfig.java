package com.jonas.jonasbank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class AppSecurityConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests ()
                .requestMatchers ("/", "/error", "/login", "/user", "/transaction", "/user/{id}", "/transaction/{id}").permitAll()
                .requestMatchers ("/adminPage" ).hasRole("ADMIN")
                .anyRequest().authenticated ()
                .and()
                .formLogin();
        http.csrf().disable();;
        return http.build();
    }
}

package com.jonas.jonasbank.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
// Enables @PreAuthorize
public class SecurityConfiguration {

    private UserDetailsService userService;
    private AppPasswordConfig bcrypt;

    public SecurityConfiguration(UserDetailsService userService, AppPasswordConfig bcrypt) {
        this.userService = userService;
        this.bcrypt = bcrypt;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests ()
                .requestMatchers ("/", "/error", "/login", "/user", "/transaction", "/user/{id}", "/transaction/{id}").permitAll()
                .requestMatchers ("/adminPage" ).hasRole("ADMIN")
                .anyRequest().authenticated ()
                .and()
                .formLogin()
                .and()
                .authenticationProvider(authenticationOverride());;
        http.csrf().disable();
        http.cors().configurationSource(request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
            corsConfiguration.addAllowedMethod("DELETE");
            corsConfiguration.addAllowedMethod("POST");
            corsConfiguration.addAllowedMethod("GET");
            corsConfiguration.addAllowedMethod("OPTIONS");
            corsConfiguration.addAllowedMethod("PUT");
            return corsConfiguration;
        });

        return http.build();
    }

    public DaoAuthenticationProvider authenticationOverride() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userService);            // Query
        provider.setPasswordEncoder(bcrypt.bCryptPasswordEncoder()); // Encoder BCRYPT

        return provider;
    }


}


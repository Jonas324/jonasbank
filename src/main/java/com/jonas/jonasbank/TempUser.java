package com.jonas.jonasbank;

import com.jonas.jonasbank.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class TempUser {

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails benny = User.withDefaultPasswordEncoder()
                .username("benny")
                .password("123")
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(benny);

    }
}

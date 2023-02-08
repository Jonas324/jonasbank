package com.jonas.jonasbank.auth;


import com.google.gson.Gson;
import com.jonas.jonasbank.config.JWTService;
import com.jonas.jonasbank.user.Role;
import com.jonas.jonasbank.user.User;
import com.jonas.jonasbank.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JWTService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        Optional<User> userOptional = userRepository.findUserByUsername(request.getUsername());
        if (userOptional.isPresent()){
            throw new IllegalStateException("username taken");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(Role.ROLE_ADMIN);
        newUser.setCredit(1000);
        newUser.setCredentialsNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setEnabled(true);
        newUser.setAccountNonExpired(true);

        userRepository.save(newUser);
        var jwtToken = jwtService.generateToken(newUser);
        Gson gson = new Gson();
        String jsonUser = gson.toJson(newUser);
        return new AuthenticationResponse(jwtToken, jsonUser);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);
        return new AuthenticationResponse(jwtToken, jsonUser);
    }
}

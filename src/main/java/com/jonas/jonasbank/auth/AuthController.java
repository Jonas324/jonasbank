package com.jonas.jonasbank.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

//    private final AuthenticationManager;
//
//    private final UserRepository userRepository;
//
//    private final PasswordEncoder passwordEncoder;

    private final AuthenticationService authService;

    @Autowired
    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    //    @Autowired
//    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
       return ResponseEntity.ok(authService.authenticate(request));
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

}

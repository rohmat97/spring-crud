package com.example.postgrescrud.controller;

import com.example.postgrescrud.model.LoginRequest;
import com.example.postgrescrud.model.User;
import com.example.postgrescrud.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// AuthController.java
@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        logger.info("Registration attempt for user: {}", user.getUsername());
        return authService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Login attempt for user: {}", loginRequest.getUsername());
        return authService.login(loginRequest);
    }

    // Additional endpoints like /login can be added here
}



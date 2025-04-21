package com.example.postgrescrud.service;

import com.example.postgrescrud.model.LoginRequest;
import com.example.postgrescrud.model.User;
import com.example.postgrescrud.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public ResponseEntity<?> register(User user) {
        logger.info("Attempting to register user with email: {}", user.getEmail());
        
        // Check if user already exists
        User existingUser = User.fromOptional(userRepository.findByEmail(user.getEmail()));
        if (existingUser != null) {
            logger.warn("Registration failed: User with email {} already exists", user.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with this email already exists");
        }

        // Validate user data
        if (user.getEmail() == null || user.getPassword() == null ||
            user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email and password are required");
        }
        
        // Hash password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Save user to database
        User savedUser = userRepository.save(user);
        logger.info("User registered successfully with ID: {}", savedUser.getId());
        
        // Return success response without exposing password
        savedUser.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        logger.info("Login attempt for user: {}", loginRequest.getEmail());
        
        // Validate request
        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null ||
            loginRequest.getEmail().isEmpty() || loginRequest.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email and password are required");
        }
        
        // Find user by email
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        
        if (userOptional.isEmpty() || 
            !passwordEncoder.matches(loginRequest.getPassword(), userOptional.get().getPassword())) {
            logger.warn("Failed login attempt for user: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
        
        User user = userOptional.get();
        logger.info("User logged in successfully: {}", user.getEmail());
        
        // Create response with user info (in a real app, you'd generate a JWT token here)
        Map<String, Object> response = new HashMap<>();
        response.put("userId", user.getId());
        response.put("email", user.getEmail());
        response.put("message", "Login successful");
        
        return ResponseEntity.ok(response);
    }
}
package com.example.customer_operations.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String loginId = credentials.get("login_id");
        String password = credentials.get("password");

        // Assuming you have a custom UserDetails service, retrieve user details from it
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);

        // Perform authentication using the provided credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginId, password, ((UserDetails) userDetails).getAuthorities())
        );

        // Check if authentication was successful
        if (authentication.isAuthenticated()) {
            // Generate the token
            String token = generateToken(loginId);

            // Create the response containing the token
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            // Return the response with the token
            return ResponseEntity.ok(response);
        } else {
            // Authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private String generateToken(String loginId) {

        return loginId + "_token";
    }
}

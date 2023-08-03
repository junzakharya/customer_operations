package com.example.customer_operations.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    // Assuming you have some users in a database or hardcoded for demo purposes
    private static final String USERNAME = "test@sunbasedata.com";
    private static final String PASSWORD = "Test@123";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (USERNAME.equals(username)) {
            // Return a User object with username, password, and an empty set of authorities
            return new User(USERNAME, PASSWORD, Collections.emptyList());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}


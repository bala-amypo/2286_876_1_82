package com.example.demo.security;

import com.example.demo.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // Used by AuthController (3 params)
    public String generateToken(Long userId, String email, String role) {
        // Dummy token for compilation/testing
        return "jwt-token-" + userId + "-" + email + "-" + role;
    }

    // Optional: used elsewhere if needed
    public String generateToken(UserAccount user) {
        return generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole().toString()
        );
    }

    // Used by JwtAuthenticationFilter
    public boolean validateToken(String token) {
        return token != null && !token.isBlank();
    }

    // Used by JwtAuthenticationFilter
    public String getUsernameFromToken(String token) {
        // Dummy extraction
        if (token == null) return null;
        return "test@example.com";
    }
}

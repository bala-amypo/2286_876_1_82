package com.example.demo.dto;

import java.util.Set;

public class AuthResponse {
    private String token;
    private Long userId;
    private String email;
    private Set<String> role;

    public AuthResponse() {}

    public AuthResponse(String token, Long userId, String email, Set<String> role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<String> getRole() { return role; }
    public void setRole(Set<String> role) { this.role = role; }
}
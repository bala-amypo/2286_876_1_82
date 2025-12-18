package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.UserAccount;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final UserAccountService userService;
    private final JwtTokenProvider jwtProvider;

    public AuthController(UserAccountService userService, JwtTokenProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public UserAccount register(@RequestBody RegisterRequest request) {
        UserAccount user = new UserAccount();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPassword());
        user.setRole(Set.of("ADMIN"));
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        String token = jwtProvider.generateToken(request.getEmail());
        return new AuthResponse(token);
    }
}

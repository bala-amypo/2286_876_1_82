package com.example.demo.service.impl;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection (REQUIRED)
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository,
                                  PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccount registerUser(UserAccount user) {
        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash())
        );
        return userAccountRepository.save(user);
    }

    @Override
    public UserAccount findByEmail(String email) {
        return userAccountRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    @Override
    public UserAccount findById(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}

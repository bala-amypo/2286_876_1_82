package com.example.demo.service;

import com.example.demo.model.UserAccount;

public interface UserAccountService {
    UserAccount findByEmail(String email);
    UserAccount save(UserAccount user);
}

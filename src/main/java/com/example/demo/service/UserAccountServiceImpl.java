package com.example.demo.service;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repository;

    public UserAccountServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAccount findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public UserAccount save(UserAccount user) {
        return repository.save(user);
    }
}


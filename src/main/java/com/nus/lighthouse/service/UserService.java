package com.nus.lighthouse.service;

import com.nus.lighthouse.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkEmailExists(String email) {
        return (userRepository.findUserByEmail(email) == null);
    }
}

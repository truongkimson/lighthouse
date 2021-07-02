package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.User;

public interface UserService {
    boolean checkEmailExists(String email);
    boolean checkEmailUpdateExists(String email, int userId);
    User findbyEmail(String username);
}

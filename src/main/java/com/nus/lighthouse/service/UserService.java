package com.nus.lighthouse.service;

public interface UserService {
    boolean checkEmailExists(String email);
    boolean checkEmailUpdateExists(String email, int userId);
}

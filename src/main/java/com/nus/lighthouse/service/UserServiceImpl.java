package com.nus.lighthouse.service;

import com.nus.lighthouse.domain.User;
import com.nus.lighthouse.repo.UserRepository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    @Override
    public boolean checkEmailExists(String email) {
        return (userRepository.findUserByEmail(email) != null);
    }
    @Transactional
    @Override
    public boolean checkEmailUpdateExists(String email, int userId) {
        if (userRepository.findUserByEmail(email) == null)
            return false;

        return !userRepository.findUserByEmail(email)
                .equals(userRepository.findById(userId).orElse(null));
    }

    @Transactional
	@Override
	public User findbyEmail(String username) {
    	User myUser= userRepository.findUserByEmail(username);
		return myUser;
	}
}

package com.kodnest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kodnest.entity.Role;
import com.kodnest.entity.User;
import com.kodnest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kodnest.entity.User;
import com.kodnest.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(User user) {

        // Check if username or email already exists
        if(userRepository.findByUsername( user.getUsername()).isPresent()){
        	throw new RuntimeException("Unser name is already Exists..");
        }
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
        	throw new RuntimeException("Email is alredy exists..");
        }
        
        return userRepository.save(user);
    }
}
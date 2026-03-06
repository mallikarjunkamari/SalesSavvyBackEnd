package com.kodnest.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kodnest.entity.User;
import com.kodnest.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {
    	User registerUser = userService.registerUser(user);
    	try {
    		return ResponseEntity.ok(Map.of("message","User registed successfully","user",registerUser));
    	}
    	catch (Exception e) {
			return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
		}
    }
    
}
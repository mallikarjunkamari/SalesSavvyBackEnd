package com.kodnest.controller;

import com.kodnest.entity.User;
import com.kodnest.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request, HttpServletResponse response) {

        try {

            // Retrieve authenticated user from request
            User user = (User) request.getAttribute("authenticatedUser");

            // Delegate logout operation to service layer
            authService.logout(user);

            // Clear authentication cookie
            Cookie cookie = new Cookie("authToken", null);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            cookie.setPath("/");

            response.addCookie(cookie);

            // Success response
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Logout successful");

            return ResponseEntity.ok(responseBody);

        } catch (RuntimeException e) {

            // Error response
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Logout failed");

            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
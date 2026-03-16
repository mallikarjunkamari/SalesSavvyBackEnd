package com.kodnest.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Service;

import java.util.Date;

import com.kodnest.entity.User;

@Service
public class AuthService {

    private static final String SECRET_KEY = "mysupersecretkeymysupersecretkey";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours

    // Generate JWT Token
    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Validate JWT Token
    public boolean validateToken(String token) {

        try {

            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            return false;

        }

    }

    // Extract Username from Token
    public String extractUsername(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // Logout Method (ADD THIS)
    public void logout(User user) {
        // If you store JWT tokens in DB you can delete them here
        System.out.println("User logged out: " + user.getUsername());
    }
}
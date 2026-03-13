package com.kodnest.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kodnest.entity.User;
import com.kodnest.repository.UserRepository;
import com.kodnest.service.CartService;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private UserRepository userRepository;

    // Fetch all cart items
    @GetMapping("/items")
    public ResponseEntity<Map<String, Object>> getCartItems(HttpServletRequest request) {

        User user = (User) request.getAttribute("authenticatedUser");

        if (user == null) {
            return ResponseEntity.status(401).body(Map.of("error", "User not authenticated"));
        }

        Map<String, Object> cartItems = cartService.getCartItems(user.getUserId());

        return ResponseEntity.ok(cartItems);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCartItem(@RequestBody Map<String, Object> request) {

        String username = (String) request.get("username");
        int productId = (int) request.get("productId");

        // Fetch user using username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found with username: " + username));

        // Delete cart item
        cartService.deleteCartItem(user.getUserId(), productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();    
       	}
}















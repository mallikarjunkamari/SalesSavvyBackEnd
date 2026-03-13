package com.kodnest.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.entity.CartItem;
import com.kodnest.entity.Product;
import com.kodnest.entity.User;
import com.kodnest.repository.CartRepository;
import com.kodnest.repository.ProductRepository;
import com.kodnest.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    // Add product to cart
    public void addToCart(int userId, int productId, int quantity) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        Optional<CartItem> existingItem = cartRepository.findByUserAndProduct(userId, productId);

        if (existingItem.isPresent()) {

            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartRepository.save(cartItem);

        } else {

            CartItem newItem = new CartItem(user, product, quantity);
            cartRepository.save(newItem);
        }
    }

    // Fetch cart items
    public Map<String, Object> getCartItems(int userId) {

    	List<CartItem> cartItems = cartRepository.findByUserUserId(userId);

    	double totalPrice = 0;

    	for (CartItem item : cartItems) {

    	    double price = item.getProduct().getPrice().doubleValue();
    	    int quantity = item.getQuantity();

    	    totalPrice += price * quantity;
    	}

    	Map<String, Object> response = new HashMap<>();

    	response.put("items", cartItems);
    	response.put("totalPrice", totalPrice);

    	return response;
    }

    public void deleteCartItem(int userId, int productId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        cartRepository.deleteCartItem(userId, productId);
    }
}
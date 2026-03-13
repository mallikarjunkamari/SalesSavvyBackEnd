package com.kodnest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kodnest.entity.CartItem;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer> {

    // Find cart item by user and product
    @Query("SELECT c FROM CartItem c WHERE c.user.userId = :userId AND c.product.productId = :productId")
    Optional<CartItem> findByUserAndProduct(int userId, int productId);

    // Get all cart items of a user
    List<CartItem> findByUserUserId(int userId);

    // Delete specific cart item
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.user.userId = :userId AND c.product.productId = :productId")
    void deleteCartItem(int userId, int productId);

    // Fetch cart items with product details (used in PaymentService)
    @Query("SELECT c FROM CartItem c JOIN FETCH c.product WHERE c.user.userId = :userId")
    List<CartItem> findCartItemsWithProductDetails(int userId);

    // Delete all cart items of a user (after payment success)
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.user.userId = :userId")
    void deleteAllCartItemsByUserId(int userId);
}
package com.unishop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-favorites")
@CrossOrigin(origins = "*")
public class UserFavoritesController {

    // Get user's favorites
    @GetMapping
    public ResponseEntity<String> getUserFavorites() {
        return ResponseEntity.ok("User favorites retrieved successfully");
    }

    // Add product to favorites
    @PostMapping("/{productId}")
    public ResponseEntity<String> addToFavorites(@PathVariable Long productId) {
        return ResponseEntity.ok("Product " + productId + " added to user favorites");
    }

    // Remove product from favorites
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeFromFavorites(@PathVariable Long productId) {
        return ResponseEntity.ok("Product " + productId + " removed from user favorites");
    }

    // Toggle favorite (add if not exists, remove if exists)
    @PostMapping("/{productId}/toggle")
    public ResponseEntity<String> toggleFavorite(@PathVariable Long productId) {
        return ResponseEntity.ok("Favorite toggled for product " + productId);
    }

    // Check if product is favorited by user
    @GetMapping("/{productId}/check")
    public ResponseEntity<String> checkFavorite(@PathVariable Long productId) {
        return ResponseEntity.ok("Product " + productId + " favorite status checked");
    }
}
package com.unishop.controller;

import com.unishop.dto.FavoriteDTO;
import com.unishop.dto.ProductDTO;
import com.unishop.service.FavoriteService;
import com.unishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
@CrossOrigin(origins = "*")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private ProductService productService;

    // Get user's favorites
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getUserFavorites() {
        // TODO: Get userId from JWT token
        Integer userId = 2; // Temporary - should come from JWT

        List<Long> favoriteProductIds = favoriteService.getUserFavoriteProductIds(userId);
        List<ProductDTO> favorites = favoriteProductIds.stream()
                .map(productService::getProductById)
                .toList();
        return ResponseEntity.ok(favorites);
    }

    // Add product to favorites
    @PostMapping("/{productId}")
    public ResponseEntity<FavoriteDTO> addToFavorites(@PathVariable Long productId) {
        // TODO: Get userId from JWT token
        Integer userId = 2; // Temporary - should come from JWT

        FavoriteDTO favorite = favoriteService.addToFavorites(userId, productId);
        return ResponseEntity.ok(favorite);
    }

    // Remove product from favorites
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromFavorites(@PathVariable Long productId) {
        // TODO: Get userId from JWT token
        Integer userId = 2; // Temporary - should come from JWT

        favoriteService.removeFromFavorites(userId, productId);
        return ResponseEntity.noContent().build();
    }

    // Toggle favorite (add if not exists, remove if exists)
    @PostMapping("/{productId}/toggle")
    public ResponseEntity<Boolean> toggleFavorite(@PathVariable Long productId) {
        // TODO: Get userId from JWT token
        Integer userId = 2; // Temporary - should come from JWT

        boolean isFavorited = favoriteService.toggleFavorite(userId, productId);
        return ResponseEntity.ok(isFavorited);
    }

    // Check if product is favorited by user
    @GetMapping("/{productId}/check")
    public ResponseEntity<Boolean> checkFavorite(@PathVariable Long productId) {
        // TODO: Get userId from JWT token
        Integer userId = 2; // Temporary - should come from JWT

        boolean isFavorited = favoriteService.isProductFavorited(userId, productId);
        return ResponseEntity.ok(isFavorited);
    }
}
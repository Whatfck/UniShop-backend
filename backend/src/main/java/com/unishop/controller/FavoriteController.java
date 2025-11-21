package com.unishop.controller;

import com.unishop.dto.ProductDTO;
import com.unishop.dto.ToggleFavoriteResponse;
import com.unishop.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<Long>> getUserFavorites() {
        // TODO: Get userId from JWT token
        Integer userId = 1; // Temporary
        List<Long> favoriteProductIds = favoriteService.getUserFavoriteProductIds(userId);
        return ResponseEntity.ok(favoriteProductIds);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<String> addToFavorites(@PathVariable Long productId) {
        // TODO: Get userId from JWT token
        Integer userId = 1; // Temporary
        favoriteService.addToFavorites(userId, productId);
        return ResponseEntity.ok("Producto agregado a favoritos");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeFromFavorites(@PathVariable Long productId) {
        // TODO: Get userId from JWT token
        Integer userId = 1; // Temporary
        favoriteService.removeFromFavorites(userId, productId);
        return ResponseEntity.ok("Producto removido de favoritos");
    }

    @PostMapping("/{productId}/toggle")
    public ResponseEntity<ToggleFavoriteResponse> toggleFavorite(@PathVariable Long productId) {
        // TODO: Get userId from JWT token
        Integer userId = 1; // Temporary
        boolean isFavorited = favoriteService.toggleFavorite(userId, productId);
        String message = isFavorited ? "Producto agregado a favoritos" : "Producto removido de favoritos";
        ToggleFavoriteResponse response = new ToggleFavoriteResponse(isFavorited, message);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}/check")
    public ResponseEntity<Boolean> checkFavorite(@PathVariable Long productId) {
        // TODO: Get userId from JWT token
        Integer userId = 1; // Temporary
        boolean isFavorited = favoriteService.isProductFavorited(userId, productId);
        return ResponseEntity.ok(isFavorited);
    }
}
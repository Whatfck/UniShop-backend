package com.unishop.service;

import com.unishop.dto.FavoriteDTO;
import com.unishop.dto.ProductDTO;
import com.unishop.entity.Favorite;
import com.unishop.repository.FavoriteRepository;
import com.unishop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;



    // Add product to favorites
    @Transactional
    public FavoriteDTO addToFavorites(Integer userId, Long productId) {
        // Check if already favorited
        if (favoriteRepository.existsByUserIdAndProductId(userId, productId)) {
            throw new RuntimeException("Product already in favorites");
        }

        // Create favorite
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setProductId(productId);

        favorite = favoriteRepository.save(favorite);
        return convertToDTO(favorite);
    }

    // Remove product from favorites
    @Transactional
    public void removeFromFavorites(Integer userId, Long productId) {
        if (!favoriteRepository.existsByUserIdAndProductId(userId, productId)) {
            throw new RuntimeException("Product not in favorites");
        }

        favoriteRepository.deleteByUserIdAndProductId(userId, productId);
    }

    // Get user's favorite product IDs
    public List<Long> getUserFavoriteProductIds(Integer userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);

        return favorites.stream()
                .map(Favorite::getProductId)
                .collect(Collectors.toList());
    }

    // Check if product is favorited by user
    public boolean isProductFavorited(Integer userId, Long productId) {
        return favoriteRepository.existsByUserIdAndProductId(userId, productId);
    }

    // Toggle favorite (add if not exists, remove if exists)
    @Transactional
    public boolean toggleFavorite(Integer userId, Long productId) {
        if (favoriteRepository.existsByUserIdAndProductId(userId, productId)) {
            favoriteRepository.deleteByUserIdAndProductId(userId, productId);
            return false; // Removed from favorites
        } else {
            Favorite favorite = new Favorite();
            favorite.setUserId(userId);
            favorite.setProductId(productId);
            favoriteRepository.save(favorite);
            return true; // Added to favorites
        }
    }

    private FavoriteDTO convertToDTO(Favorite favorite) {
        FavoriteDTO dto = new FavoriteDTO();
        dto.setId(favorite.getId());
        dto.setUserId(favorite.getUserId());
        dto.setProductId(favorite.getProductId());
        dto.setCreatedAt(favorite.getCreatedAt());
        return dto;
    }
}
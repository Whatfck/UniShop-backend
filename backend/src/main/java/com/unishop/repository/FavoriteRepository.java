package com.unishop.repository;

import com.unishop.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    // Find all favorites for a user
    List<Favorite> findByUserId(Integer userId);

    // Check if a user has favorited a specific product
    boolean existsByUserIdAndProductId(Integer userId, Long productId);

    // Find a specific favorite by user and product
    Optional<Favorite> findByUserIdAndProductId(Integer userId, Long productId);

    // Delete a favorite by user and product
    void deleteByUserIdAndProductId(Integer userId, Long productId);

    // Get favorites with product details for a user
    @Query("SELECT f FROM Favorite f WHERE f.userId = :userId")
    List<Favorite> findFavoritesWithProductsByUserId(@Param("userId") Integer userId);
}
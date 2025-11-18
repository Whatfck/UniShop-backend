package com.unishop.repository;

import com.unishop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByUserId(Long userId);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByStatus(String status);

    @Query("SELECT p FROM Product p WHERE p.status = 'ACTIVE' AND " +
           "(LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "EXISTS (SELECT c FROM Category c WHERE c.id = p.categoryId AND LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%'))))")
    List<Product> searchActiveProducts(@Param("query") String query);
}
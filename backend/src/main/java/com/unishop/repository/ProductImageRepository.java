package com.unishop.repository;

import com.unishop.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductIdOrderByOrderIndex(Long productId);

    List<ProductImage> findByProductIdAndIsPrimaryTrue(Long productId);
}
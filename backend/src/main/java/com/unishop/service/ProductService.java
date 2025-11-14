package com.unishop.service;

import com.unishop.dto.CreateProductRequest;
import com.unishop.dto.ProductDTO;
import com.unishop.dto.ProductImageDTO;
import com.unishop.entity.Product;
import com.unishop.entity.ProductImage;
import com.unishop.repository.ProductRepository;
import com.unishop.repository.ProductImageRepository;
import com.unishop.repository.UserRepository;
import com.unishop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAllActiveProducts() {
        List<Product> products = productRepository.findByStatus("ACTIVE");
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDTO(product);
    }

    public ProductDTO createProduct(CreateProductRequest request, Long userId) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setUserId(userId);
        product.setCategoryId(request.getCategoryId());

        product = productRepository.save(product);

        // Handle images if provided
        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
            for (int i = 0; i < request.getImageUrls().size(); i++) {
                ProductImage image = new ProductImage();
                image.setProductId(product.getId());
                image.setImageUrl(request.getImageUrls().get(i));
                image.setIsPrimary(i == 0); // First image is primary
                image.setOrderIndex(i);
                productImageRepository.save(image);
            }
        }

        return convertToDTO(product);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStatus(product.getStatus());
        dto.setUserId(product.getUserId());
        dto.setCategoryId(product.getCategoryId());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());

        // Load user name
        userRepository.findById(product.getUserId()).ifPresent(user ->
            dto.setUserName(user.getName())
        );

        // Load category name
        categoryRepository.findById(product.getCategoryId()).ifPresent(category ->
            dto.setCategoryName(category.getName())
        );

        // Load and convert images
        List<ProductImage> images = productImageRepository.findByProductIdOrderByOrderIndex(product.getId());
        List<ProductImageDTO> imageDTOs = images.stream()
            .map(this::convertImageToDTO)
            .collect(Collectors.toList());
        dto.setImages(imageDTOs);

        return dto;
    }

    private ProductImageDTO convertImageToDTO(ProductImage image) {
        ProductImageDTO dto = new ProductImageDTO();
        dto.setId(image.getId());
        dto.setProductId(image.getProductId());
        dto.setImageUrl(image.getImageUrl());
        dto.setIsPrimary(image.getIsPrimary());
        dto.setOrderIndex(image.getOrderIndex());
        return dto;
    }
}
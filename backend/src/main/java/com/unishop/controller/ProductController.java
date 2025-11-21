package com.unishop.controller;

import com.unishop.dto.CreateProductRequest;
import com.unishop.dto.ProductDTO;
import com.unishop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String condition,
            @RequestParam(required = false) String datePosted) {
        List<ProductDTO> products = productService.getFilteredProducts(
            search != null ? search.trim() : null,
            categoryId,
            minPrice,
            maxPrice,
            condition,
            datePosted
        );
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        // TODO: isFavorited will be checked by frontend via separate API call
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody CreateProductRequest request) {
        // TODO: Get userId from JWT token
        Long userId = 1L; // Temporary - using small value to avoid overflow
        ProductDTO product = productService.createProduct(request, userId);
        return ResponseEntity.ok(product);
    }
}
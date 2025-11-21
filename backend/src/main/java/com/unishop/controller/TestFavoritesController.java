package com.unishop.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test-favorites")
public class TestFavoritesController {

    @GetMapping
    public String getTestFavorites() {
        return "Test favorites working!";
    }

    @PostMapping("/{id}")
    public String addTestFavorite(@PathVariable String id) {
        return "Added test favorite: " + id;
    }
}
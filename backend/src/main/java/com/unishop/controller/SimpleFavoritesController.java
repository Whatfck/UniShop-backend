package com.unishop.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/simple-favorites")
public class SimpleFavoritesController {

    @GetMapping
    public String getFavorites() {
        return "Simple favorites working!";
    }

    @PostMapping("/{id}")
    public String addFavorite(@PathVariable String id) {
        return "Added favorite: " + id;
    }

    @DeleteMapping("/{id}")
    public String removeFavorite(@PathVariable String id) {
        return "Removed favorite: " + id;
    }
}
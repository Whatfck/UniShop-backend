package com.unishop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test controller working!");
    }

    @GetMapping("/favorites")
    public ResponseEntity<String> testFavorites() {
        return ResponseEntity.ok("Test favorites working!");
    }
}
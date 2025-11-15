package com.unishop.controller;

import com.unishop.dto.AuthRequest;
import com.unishop.dto.AuthResponse;
import com.unishop.dto.RegisterRequest;
import com.unishop.entity.User;
import com.unishop.service.AuthService;
import com.unishop.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<AuthResponse.UserInfo> getProfile(@RequestHeader("Authorization") String token) {
        // Remove "Bearer " prefix
        String jwtToken = token.replace("Bearer ", "");
        String email = jwtService.extractUsername(jwtToken);

        // Get user from database (you might want to create a UserService for this)
        User user = authService.getUserByEmail(email);

        AuthResponse.UserInfo userInfo = new AuthResponse.UserInfo(
            user.getId().toString(),
            user.getName(),
            user.getEmail(),
            user.getRole(),
            user.getProfileImage()
        );

        return ResponseEntity.ok(userInfo);
    }
}
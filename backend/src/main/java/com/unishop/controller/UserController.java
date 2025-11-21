package com.unishop.controller;

import com.unishop.dto.UpdateProfileRequest;
import com.unishop.dto.UserProfileDTO;
import com.unishop.entity.User;
import com.unishop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfileDTO profile = new UserProfileDTO();
        profile.setId(user.getId());
        profile.setName(user.getName());
        profile.setEmail(user.getEmail());
        profile.setAvatar(user.getProfileImage());
        profile.setPhoneVerified(user.getPhoneVerified());
        profile.setCreatedAt(user.getCreatedAt());

        return ResponseEntity.ok(profile);
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update fields
        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getProfileImage() != null) {
            user.setProfileImage(request.getProfileImage());
        }

        user.setUpdatedAt(LocalDateTime.now());
        user = userRepository.save(user);

        UserProfileDTO profile = new UserProfileDTO();
        profile.setId(user.getId());
        profile.setName(user.getName());
        profile.setEmail(user.getEmail());
        profile.setAvatar(user.getProfileImage());
        profile.setPhoneVerified(user.getPhoneVerified());
        profile.setCreatedAt(user.getCreatedAt());

        return ResponseEntity.ok(profile);
    }
}
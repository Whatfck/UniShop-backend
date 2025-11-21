package com.unishop.controller;

import com.unishop.dto.ImageUploadResponse;
import com.unishop.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    @Autowired
    private FileStorageService fileStorageService;

    private static final AtomicLong imageIdCounter = new AtomicLong(1);

    @PostMapping("/products/upload")
    public ResponseEntity<List<ImageUploadResponse>> uploadProductImages(
            @RequestParam("files") MultipartFile[] files) {

        List<ImageUploadResponse> responses = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                String url = fileStorageService.storeProductImage(file);

                ImageUploadResponse response = new ImageUploadResponse(
                    imageIdCounter.getAndIncrement(),
                    file.getOriginalFilename(),
                    url,
                    file.getSize(),
                    file.getContentType()
                );

                responses.add(response);

            } catch (Exception e) {
                // Log error and continue with other files
                System.err.println("Error uploading file: " + file.getOriginalFilename() + " - " + e.getMessage());
            }
        }

        return ResponseEntity.ok(responses);
    }

    @PostMapping("/profiles/upload")
    public ResponseEntity<ImageUploadResponse> uploadProfileImage(
            @RequestParam("file") MultipartFile file) {

        try {
            String url = fileStorageService.storeProfileImage(file);

            ImageUploadResponse response = new ImageUploadResponse(
                imageIdCounter.getAndIncrement(),
                file.getOriginalFilename(),
                url,
                file.getSize(),
                file.getContentType()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteImage(@RequestParam("url") String url) {
        try {
            fileStorageService.deleteFile(url);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
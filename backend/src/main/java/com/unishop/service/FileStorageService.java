package com.unishop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path rootLocation = Paths.get("/app/uploads");

    public FileStorageService() {
        try {
            Files.createDirectories(rootLocation);
            Files.createDirectories(rootLocation.resolve("products"));
            Files.createDirectories(rootLocation.resolve("profiles"));
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    public String storeProductImage(MultipartFile file) {
        return storeFile(file, "products");
    }

    public String storeProfileImage(MultipartFile file) {
        return storeFile(file, "profiles");
    }

    private String storeFile(MultipartFile file, String subfolder) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }

            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String filename = UUID.randomUUID().toString() + extension;
            Path targetLocation = this.rootLocation.resolve(subfolder).resolve(filename);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Return relative path for database storage (accessible via /uploads/)
            return "/uploads/" + subfolder + "/" + filename;

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    public void deleteFile(String filePath) {
        try {
            // Remove /uploads/ prefix to get relative path
            String relativePath = filePath.replaceFirst("^/uploads/", "");
            Path file = rootLocation.resolve(relativePath);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            // Log error but don't throw exception for delete operations
            System.err.println("Failed to delete file: " + filePath);
        }
    }
}
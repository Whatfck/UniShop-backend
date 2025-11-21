package com.unishop.dto;

import java.time.LocalDateTime;

public class ImageUploadResponse {

    private Long id;
    private String filename;
    private String url;
    private Long size;
    private String contentType;
    private LocalDateTime uploadedAt;

    // Constructors
    public ImageUploadResponse() {}

    public ImageUploadResponse(Long id, String filename, String url, Long size, String contentType) {
        this.id = id;
        this.filename = filename;
        this.url = url;
        this.size = size;
        this.contentType = contentType;
        this.uploadedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
package com.unishop.dto;

public class ToggleFavoriteResponse {

    private boolean isFavorited;
    private String message;

    // Constructors
    public ToggleFavoriteResponse() {}

    public ToggleFavoriteResponse(boolean isFavorited, String message) {
        this.isFavorited = isFavorited;
        this.message = message;
    }

    // Getters and Setters
    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean isFavorited) {
        this.isFavorited = isFavorited;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
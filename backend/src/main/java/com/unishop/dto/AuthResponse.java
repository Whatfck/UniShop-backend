package com.unishop.dto;

public class AuthResponse {

    private String access_token;
    private UserInfo user;

    // Inner class for user info
    public static class UserInfo {
        private String id;
        private String name;
        private String email;
        private String role;
        private String profilePictureUrl;

        public UserInfo() {}

        public UserInfo(String id, String name, String email, String role, String profilePictureUrl) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.role = role;
            this.profilePictureUrl = profilePictureUrl;
        }

        // Getters and Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }

        public String getProfilePictureUrl() { return profilePictureUrl; }
        public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }
    }

    // Constructors
    public AuthResponse() {}

    public AuthResponse(String token, Long id, String email, String name, String role) {
        this.access_token = token;
        this.user = new UserInfo(id.toString(), name, email, role, null);
    }

    // Getters and Setters
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
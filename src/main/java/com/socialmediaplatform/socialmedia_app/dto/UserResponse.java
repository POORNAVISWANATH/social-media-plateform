package com.socialmediaplatform.socialmedia_app.dto;

import java.time.LocalDateTime;

public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String bio;
    private LocalDateTime createdAt;

    public UserResponse(Long id, String username, String email, String bio, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.createdAt = createdAt;
    }

    // getters only — this object is only ever built once, then sent out; nothing should mutate it afterward
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getBio() { return bio; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}

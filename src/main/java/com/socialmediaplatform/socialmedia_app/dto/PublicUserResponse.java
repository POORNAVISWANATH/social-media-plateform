package com.socialmediaplatform.socialmedia_app.dto;

import java.time.LocalDateTime;

public class PublicUserResponse {
    private Long id;
    private String username;
    private String bio;
    private LocalDateTime createdAt;

    public PublicUserResponse(Long id, String username, String bio, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.bio = bio;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getBio() { return bio; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}

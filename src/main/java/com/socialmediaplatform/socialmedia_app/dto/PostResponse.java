package com.socialmediaplatform.socialmedia_app.dto;

import java.time.LocalDateTime;

public class PostResponse {
    private Long id;
    private AuthorSummary author;
    private String content;
    private Long likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostResponse(Long id, AuthorSummary author, String content,Long likeCount,
                        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public AuthorSummary getAuthor() { return author; }
    public String getContent() { return content; }
    public long getLikeCount() { return likeCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public static class AuthorSummary {
        private Long id;
        private String username;

        public AuthorSummary(Long id, String username) {
            this.id = id;
            this.username = username;
        }

        public Long getId() { return id; }
        public String getUsername() { return username; }
    }
}

package com.socialmediaplatform.socialmedia_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostRequest {
    @NotNull(message = "Author ID is required")   // TODO Phase 3: remove once auth provides this
    private Long authorId;

    @NotBlank(message = "Content is required")
    @Size(max = 2000, message = "Post content cannot exceed 2000 characters")
    private String content;

    public PostRequest() {}

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}

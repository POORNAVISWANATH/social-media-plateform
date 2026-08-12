package com.socialmediaplatform.socialmedia_app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "follows",
        uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id","followed_id"})
)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_id", nullable = false)
    private User followed;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Follow() {}

    public Follow(User follower, User followed) {
        if (follower.getId() != null && follower.getId().equals(followed.getId())) {
            throw new IllegalArgumentException("A user cannot follow themselves.");
        }
        this.follower = follower;
        this.followed = followed;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public User getFollower() { return follower; }
    public User getFollowed() { return followed; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}

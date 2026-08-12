package com.socialmediaplatform.socialmedia_app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {
    public enum Type { LIKE, COMMENT, FOLLOW }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "like_id")
    private Like like;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follow_id")
    private Follow follow;

    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Notification() {}

    public static Notification forLike(User recipient, Like like) {
        Notification n = new Notification();
        n.recipient = recipient;
        n.type = Type.LIKE;
        n.like = like;
        return n;
    }

    public static Notification forComment(User recipient, Comment comment) {
        Notification n = new Notification();
        n.recipient = recipient;
        n.type = Type.COMMENT;
        n.comment = comment;
        return n;
    }

    public static Notification forFollow(User recipient, Follow follow) {
        Notification n = new Notification();
        n.recipient = recipient;
        n.type = Type.FOLLOW;
        n.follow = follow;
        return n;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public User getRecipient() { return recipient; }
    public Type getType() { return type; }
    public Like getLike() { return like; }
    public Comment getComment() { return comment; }
    public Follow getFollow() { return follow; }
    public boolean isRead() { return isRead; }
    public void markAsRead() { this.isRead = true; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}

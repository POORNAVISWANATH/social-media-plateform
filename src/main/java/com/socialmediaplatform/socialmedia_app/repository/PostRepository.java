package com.socialmediaplatform.socialmedia_app.repository;

import com.socialmediaplatform.socialmedia_app.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthorIdOrderByCreatedAtDesc(Long authorId);


    List<Post> findByAuthorIdInOrderByCreatedAtDesc(List<Long> authorIds);

    long countByAuthorId(Long authorId);
}

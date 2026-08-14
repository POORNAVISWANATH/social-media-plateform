package com.socialmediaplatform.socialmedia_app.repository;

import com.socialmediaplatform.socialmedia_app.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    boolean existsByFollowerIdAndFollowedId(Long followerId, Long followedId);
    void deleteByFollowerIdAndFollowedId(Long followerId, Long followedId);
    List<Long> findFollowedIdByFollowerId(Long followerId);
    long countByFollowedId(Long userId); // followers count
    long countByFollowerId(Long userId); // following count
}
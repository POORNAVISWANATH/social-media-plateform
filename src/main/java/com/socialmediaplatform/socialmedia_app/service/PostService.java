package com.socialmediaplatform.socialmedia_app.service;

import com.socialmediaplatform.socialmedia_app.dto.PostRequest;
import com.socialmediaplatform.socialmedia_app.dto.PostResponse;
import com.socialmediaplatform.socialmedia_app.entity.Post;
import com.socialmediaplatform.socialmedia_app.entity.User;
import com.socialmediaplatform.socialmedia_app.exception.ResourceNotFoundException;
import com.socialmediaplatform.socialmedia_app.repository.LikeRepository;
import com.socialmediaplatform.socialmedia_app.repository.PostRepository;
import com.socialmediaplatform.socialmedia_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    public PostResponse createPost(PostRequest request) {
        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + request.getAuthorId()));

        Post post = new Post(author, request.getContent());
        Post savedPost = postRepository.save(post);

        return toPostResponse(savedPost);
    }

    private PostResponse toPostResponse(Post post) {
        Long likeCount = likeRepository.countByPostId(post.getId());
        return new PostResponse(
                post.getId(),
                new PostResponse.AuthorSummary(post.getAuthor().getId(), post.getAuthor().getUsername()),
                post.getContent(),
                likeCount,
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

    public PostResponse getPostById(Long id) {
        Post post  = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post with id: " + id + " not found")
        );

        return toPostResponse(post);
    }

    //Load all the user's Post
    public List<PostResponse> getPostsByAuthorId(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        List<PostResponse> allPosts = postRepository.findByAuthorIdOrderByCreatedAtDesc(id)
                .stream().map(post -> toPostResponse(post))
                .toList();
        return allPosts;
    }
}

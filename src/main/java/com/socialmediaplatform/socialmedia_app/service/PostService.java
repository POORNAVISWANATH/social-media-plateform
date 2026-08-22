package com.socialmediaplatform.socialmedia_app.service;

import com.socialmediaplatform.socialmedia_app.dto.PostRequest;
import com.socialmediaplatform.socialmedia_app.dto.PostResponse;
import com.socialmediaplatform.socialmedia_app.entity.Post;
import com.socialmediaplatform.socialmedia_app.entity.User;
import com.socialmediaplatform.socialmedia_app.exception.ResourceNotFoundException;
import com.socialmediaplatform.socialmedia_app.repository.PostRepository;
import com.socialmediaplatform.socialmedia_app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
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
        return new PostResponse(
                post.getId(),
                new PostResponse.AuthorSummary(post.getAuthor().getId(), post.getAuthor().getUsername()),
                post.getContent(),
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
}

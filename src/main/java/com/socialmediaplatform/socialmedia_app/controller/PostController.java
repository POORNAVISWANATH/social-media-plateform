package com.socialmediaplatform.socialmedia_app.controller;

import com.socialmediaplatform.socialmedia_app.dto.PostRequest;
import com.socialmediaplatform.socialmedia_app.dto.PostResponse;
import com.socialmediaplatform.socialmedia_app.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<PostResponse> post(@Valid @RequestBody PostRequest postRequest) {
        PostResponse response = postService.createPost(postRequest);
        URI location = URI.create("/api/posts/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        PostResponse response = postService.getPostById(id);
        return ResponseEntity.ok(response);
    }
}

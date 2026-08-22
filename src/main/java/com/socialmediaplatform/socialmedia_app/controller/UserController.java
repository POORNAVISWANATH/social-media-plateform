package com.socialmediaplatform.socialmedia_app.controller;

import com.socialmediaplatform.socialmedia_app.dto.PostResponse;
import com.socialmediaplatform.socialmedia_app.dto.PublicUserResponse;
import com.socialmediaplatform.socialmedia_app.dto.RegisterRequest;
import com.socialmediaplatform.socialmedia_app.dto.UserResponse;
import com.socialmediaplatform.socialmedia_app.service.PostService;
import com.socialmediaplatform.socialmedia_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = userService.register(request);
        URI location = URI.create("/api/users/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicUserResponse> getUser(@PathVariable Long id) {
        PublicUserResponse user = userService.loadUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostResponse>> getUserPosts(@PathVariable Long id) {
        List<PostResponse> userPosts = postService.getPostsByAuthorId(id);
        return ResponseEntity.ok(userPosts);
    }
}

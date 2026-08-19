package com.socialmediaplatform.socialmedia_app.controller;

import com.socialmediaplatform.socialmedia_app.dto.RegisterRequest;
import com.socialmediaplatform.socialmedia_app.dto.UserResponse;
import com.socialmediaplatform.socialmedia_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = userService.register(request);
        URI location = URI.create("/api/users/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }
}

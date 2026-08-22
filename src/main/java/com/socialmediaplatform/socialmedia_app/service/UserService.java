package com.socialmediaplatform.socialmedia_app.service;

import com.socialmediaplatform.socialmedia_app.dto.PublicUserResponse;
import com.socialmediaplatform.socialmedia_app.dto.RegisterRequest;
import com.socialmediaplatform.socialmedia_app.dto.UserResponse;

import com.socialmediaplatform.socialmedia_app.entity.User;

import com.socialmediaplatform.socialmedia_app.exception.EmailAlreadyExistsException;
import com.socialmediaplatform.socialmedia_app.exception.ResourceNotFoundException;
import com.socialmediaplatform.socialmedia_app.exception.UsernameAlreadyExistsException;
import com.socialmediaplatform.socialmedia_app.repository.UserRepository;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException(request.getUsername());
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }
        // TODO Phase 3: hash request.getPassword() before storing — storing raw for now
        User user = new User(request.getUsername(), request.getEmail(), request.getPassword());
        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getBio(),
                savedUser.getCreatedAt()
        );
    }

    //load all user details
    public PublicUserResponse loadUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        return new PublicUserResponse(
                user.getId(),
                user.getUsername(),
                user.getBio(),
                user.getCreatedAt()
        );
    }

}

package com.project_manager.project.services;

import org.springframework.stereotype.Service;

import com.project_manager.project.dto.UserRequest;
import com.project_manager.project.dto.UserResponse;
import com.project_manager.project.entity.User;
import com.project_manager.project.exception.ResourceNotFoundException;
import com.project_manager.project.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Convert User entity to UserResponse
    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getName(),
                user.getEmail()
        );
    }

    // Create a user
    public UserResponse createUser(UserRequest request) {
        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    // Get all users
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get user by ID
    public UserResponse getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with ID: " + userId));

        return mapToResponse(user);
    }

    // Update user
    public UserResponse updateUser(UUID userId, UserRequest request) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with ID: " + userId));

        existingUser.setName(request.name());
        existingUser.setEmail(request.email());
        existingUser.setPassword(request.password());

        User updatedUser = userRepository.save(existingUser);

        return mapToResponse(updatedUser);
    }

    // Delete user
    public void deleteUser(UUID userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with ID: " + userId));

        userRepository.delete(existingUser);
    }
}
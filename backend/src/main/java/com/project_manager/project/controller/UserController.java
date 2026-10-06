package com.project_manager.project.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import com.project_manager.project.dto.UserRequest;
import com.project_manager.project.dto.UserResponse;
import com.project_manager.project.services.UserServices;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    // Create a user
    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userServices.createUser(request));
    }

    // Get all users
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userServices.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable UUID userId) {
        return userServices.getUserById(userId);
    }

    // Update user
    @PutMapping("/{userId}")
    public UserResponse updateUser(
            @PathVariable UUID userId,
            @Valid @RequestBody UserRequest request) {

        return userServices.updateUser(userId, request);
    }

    // Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable UUID userId) {

        userServices.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }
}
package com.project_manager.project.services;

import org.springframework.stereotype.Service;
import com.project_manager.project.entity.User;
import com.project_manager.project.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found with ID: " + userId));
    }

    // Update user
    public User updateUser(UUID userId, User updatedUser) {
        User existingUser = getUserById(userId);

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        return userRepository.save(existingUser);
    }

    // Delete user
    public void deleteUser(UUID userId) {
        User existingUser = getUserById(userId);
        userRepository.delete(existingUser);
    }
}
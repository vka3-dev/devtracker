package com.project_manager.project.dto;

import java.util.UUID;

public record UserResponse(
        UUID userId,
        String name,
        String email
) {}
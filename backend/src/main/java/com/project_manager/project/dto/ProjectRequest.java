package com.project_manager.project.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProjectRequest(

        @NotBlank(message = "Project name is required")
        String name,

        String description,

        @NotBlank(message = "Status is required")
        String status,

        @NotNull(message = "Deadline is required")
        LocalDate deadline,

        @NotNull(message = "User ID is required")
        UUID userId

) {}
package com.project_manager.project.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequest(

        @NotBlank(message = "Task title is required")
        String title,

        String description,

        @NotBlank(message = "Status is required")
        String status,

        @NotBlank(message = "Priority is required")
        String priority,

        @NotNull(message = "Due date is required")
        LocalDate dueDate,

        @NotNull(message = "Project ID is required")
        Long projectId

) {}
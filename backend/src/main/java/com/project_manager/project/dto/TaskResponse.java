package com.project_manager.project.dto;

import java.time.LocalDate;

public record TaskResponse(
        Long taskId,
        String title,
        String description,
        String status,
        String priority,
        LocalDate dueDate,
        Long projectId
) {}

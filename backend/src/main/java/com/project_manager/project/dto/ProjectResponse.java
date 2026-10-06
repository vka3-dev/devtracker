package com.project_manager.project.dto;

import java.time.LocalDate;
import java.util.UUID;

public record ProjectResponse(
        Long projectId,
        String name,
        String description,
        String status,
        LocalDate deadline,
        UUID userId
) {}

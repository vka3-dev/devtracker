package com.project_manager.project.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import com.project_manager.project.dto.ProjectRequest;
import com.project_manager.project.dto.ProjectResponse;
import com.project_manager.project.services.ProjectServices;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectServices projectServices;

    public ProjectController(ProjectServices projectServices) {
        this.projectServices = projectServices;
    }

    // Create a project
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @Valid @RequestBody ProjectRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectServices.createProject(request));
    }

    // Get all projects
    @GetMapping
    public List<ProjectResponse> getAllProjects() {
        return projectServices.getAllProjects();
    }

    // Get project by ID
    @GetMapping("/{projectId}")
    public ProjectResponse getProjectById(
            @PathVariable Long projectId) {

        return projectServices.getProjectById(projectId);
    }

    // Get projects by user ID
    @GetMapping("/user/{userId}")
    public List<ProjectResponse> getProjectsByUserId(
            @PathVariable UUID userId) {

        return projectServices.getProjectsByUserId(userId);
    }

    // Update project
    @PutMapping("/{projectId}")
    public ProjectResponse updateProject(
            @PathVariable Long projectId,
            @Valid @RequestBody ProjectRequest request) {

        return projectServices.updateProject(projectId, request);
    }

    // Delete project
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long projectId) {

        projectServices.deleteProject(projectId);

        return ResponseEntity.noContent().build();
    }
}
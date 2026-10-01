
package com.project_manager.project.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project_manager.project.entity.Project;
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
    public ResponseEntity<Project> createProject(
            @RequestBody Project project,
            @RequestParam UUID userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectServices.createProject(project, userId));
    }

    // Get all projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectServices.getAllProjects();
    }

    // Get project by ID
    @GetMapping("/{projectId}")
    public Project getProjectById(@PathVariable Long projectId) {
        return projectServices.getProjectById(projectId);
    }

    // Get projects by user ID
    @GetMapping("/user/{userId}")
    public List<Project> getProjectsByUserId(
            @PathVariable UUID userId) {
        return projectServices.getProjectsByUserId(userId);
    }

    // Update project
    @PutMapping("/{projectId}")
    public Project updateProject(
            @PathVariable Long projectId,
            @RequestBody Project project) {
        return projectServices.updateProject(projectId, project);
    }

    // Delete project
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long projectId) {
        projectServices.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }
}

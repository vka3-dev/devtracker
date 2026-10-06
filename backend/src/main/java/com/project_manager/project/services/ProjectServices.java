package com.project_manager.project.services;

import org.springframework.stereotype.Service;

import com.project_manager.project.dto.ProjectRequest;
import com.project_manager.project.dto.ProjectResponse;
import com.project_manager.project.entity.Project;
import com.project_manager.project.entity.User;
import com.project_manager.project.exception.ResourceNotFoundException;
import com.project_manager.project.repository.ProjectRepository;
import com.project_manager.project.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServices {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServices(
            ProjectRepository projectRepository,
            UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    // Convert Project entity to ProjectResponse
    private ProjectResponse mapToResponse(Project project) {
        return new ProjectResponse(
                project.getProjectId(),
                project.getName(),
                project.getDescription(),
                project.getStatus(),
                project.getDeadline(),
                project.getUser().getUserId()
        );
    }

    // Create a project
    public ProjectResponse createProject(ProjectRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with ID: " + request.userId()));

        Project project = new Project();

        project.setName(request.name());
        project.setDescription(request.description());
        project.setStatus(request.status());
        project.setDeadline(request.deadline());
        project.setUser(user);

        Project savedProject = projectRepository.save(project);

        return mapToResponse(savedProject);
    }

    // Get all projects
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get project by ID
    public ProjectResponse getProjectById(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found with ID: " + projectId));

        return mapToResponse(project);
    }

    // Get all projects belonging to a user
    public List<ProjectResponse> getProjectsByUserId(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with ID: " + userId));

        return projectRepository.findAll()
                .stream()
                .filter(project -> project.getUser().getUserId()
                        .equals(user.getUserId()))
                .map(this::mapToResponse)
                .toList();
    }

    // Update a project
    public ProjectResponse updateProject(
            Long projectId,
            ProjectRequest request) {

        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found with ID: " + projectId));

        existingProject.setName(request.name());
        existingProject.setDescription(request.description());
        existingProject.setStatus(request.status());
        existingProject.setDeadline(request.deadline());

        Project updatedProject = projectRepository.save(existingProject);

        return mapToResponse(updatedProject);
    }

    // Delete a project
    public void deleteProject(Long projectId) {

        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found with ID: " + projectId));

        projectRepository.delete(existingProject);
    }
}
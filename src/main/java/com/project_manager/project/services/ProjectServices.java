
package com.project_manager.project.services;

import org.springframework.stereotype.Service;

import com.project_manager.project.entity.Project;
import com.project_manager.project.entity.User;
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

    public Project createProject(Project project, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found with ID: " + userId));

        project.setUser(user);

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Get project by ID
    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Project not found with ID: " + projectId));
    }

    // Get all projects belonging to a user
    public List<Project> getProjectsByUserId(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found with ID: " + userId));

        return projectRepository.findAll()
                .stream()
                .filter(project -> project.getUser().getUserId()
                        .equals(user.getUserId()))
                .toList();
    }

    // Update a project
    public Project updateProject(Long projectId, Project updatedProject) {
        Project existingProject = getProjectById(projectId);

        existingProject.setName(updatedProject.getName());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setStatus(updatedProject.getStatus());
        existingProject.setDeadline(updatedProject.getDeadline());

        return projectRepository.save(existingProject);
    }

    // Delete a project
    public void deleteProject(Long projectId) {
        Project existingProject = getProjectById(projectId);
        projectRepository.delete(existingProject);
    }
}

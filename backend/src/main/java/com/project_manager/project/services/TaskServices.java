package com.project_manager.project.services;

import org.springframework.stereotype.Service;

import com.project_manager.project.dto.TaskRequest;
import com.project_manager.project.dto.TaskResponse;
import com.project_manager.project.entity.Task;
import com.project_manager.project.entity.Project;
import com.project_manager.project.exception.ResourceNotFoundException;
import com.project_manager.project.repository.TaskRepository;
import com.project_manager.project.repository.ProjectRepository;

import java.util.List;

@Service
public class TaskServices {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskServices(
            TaskRepository taskRepository,
            ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    // Convert Task entity to TaskResponse
    private TaskResponse mapToResponse(Task task) {
        return new TaskResponse(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getProject().getProjectId()
        );
    }

    // Create a task
    public TaskResponse createTask(TaskRequest request) {

        Project project = projectRepository.findById(request.projectId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found with ID: " + request.projectId()));

        Task task = new Task();

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setPriority(request.priority());
        task.setDueDate(request.dueDate());
        task.setProject(project);

        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }

    // Get all tasks
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get task by ID
    public TaskResponse getTaskById(Long taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found with ID: " + taskId));

        return mapToResponse(task);
    }

    // Get all tasks belonging to a project
    public List<TaskResponse> getTasksByProjectId(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found with ID: " + projectId));

        return taskRepository.findAll()
                .stream()
                .filter(task -> task.getProject().getProjectId()
                        .equals(project.getProjectId()))
                .map(this::mapToResponse)
                .toList();
    }

    // Update a task
    public TaskResponse updateTask(
            Long taskId,
            TaskRequest request) {

        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found with ID: " + taskId));

        existingTask.setTitle(request.title());
        existingTask.setDescription(request.description());
        existingTask.setStatus(request.status());
        existingTask.setPriority(request.priority());
        existingTask.setDueDate(request.dueDate());

        Task updatedTask = taskRepository.save(existingTask);

        return mapToResponse(updatedTask);
    }

    // Delete a task
    public void deleteTask(Long taskId) {

        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Task not found with ID: " + taskId));

        taskRepository.delete(existingTask);
    }
}
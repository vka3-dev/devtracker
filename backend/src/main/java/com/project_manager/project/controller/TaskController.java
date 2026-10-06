package com.project_manager.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.project_manager.project.dto.TaskRequest;
import com.project_manager.project.dto.TaskResponse;
import com.project_manager.project.services.TaskServices;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServices taskService;

    public TaskController(TaskServices taskService) {
        this.taskService = taskService;
    }

    // Create a task
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(request));
    }

    // Get all tasks
    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Get task by ID
    @GetMapping("/{taskId}")
    public TaskResponse getTaskById(
            @PathVariable Long taskId) {

        return taskService.getTaskById(taskId);
    }

    // Get tasks by project ID
    @GetMapping("/project/{projectId}")
    public List<TaskResponse> getTasksByProjectId(
            @PathVariable Long projectId) {

        return taskService.getTasksByProjectId(projectId);
    }

    // Update a task
    @PutMapping("/{taskId}")
    public TaskResponse updateTask(
            @PathVariable Long taskId,
            @Valid @RequestBody TaskRequest request) {

        return taskService.updateTask(taskId, request);
    }

    // Delete a task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long taskId) {

        taskService.deleteTask(taskId);

        return ResponseEntity.noContent().build();
    }
}

package com.project_manager.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project_manager.project.entity.Task;
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
    public ResponseEntity<Task> createTask(
            @RequestParam Long projectId,
            @RequestBody Task task) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(task, projectId));
    }

    // Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Get task by ID
    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    // Get tasks by project ID
    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProjectId(
            @PathVariable Long projectId) {
        return taskService.getTasksByProjectId(projectId);
    }

    // Update a task
    @PutMapping("/{taskId}")
    public Task updateTask(
            @PathVariable Long taskId,
            @RequestBody Task task) {
        return taskService.updateTask(taskId, task);
    }

    // Delete a task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}

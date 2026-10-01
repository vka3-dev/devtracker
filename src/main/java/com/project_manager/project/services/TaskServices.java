
package com.project_manager.project.services;
import org.springframework.stereotype.Service;

import com.project_manager.project.entity.Task;
import com.project_manager.project.entity.Project;
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

    // Create a task
    public Task createTask(Task task, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Project not found with ID: " + projectId));

        task.setProject(project);

        return taskRepository.save(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by ID
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Task not found with ID: " + taskId));
    }

    // Get all tasks belonging to a project
    public List<Task> getTasksByProjectId(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Project not found with ID: " + projectId));

        return taskRepository.findAll()
                .stream()
                .filter(task -> task.getProject().getProjectId()
                        .equals(project.getProjectId()))
                .toList();
    }

    // Update a task
    public Task updateTask(Long taskId, Task updatedTask) {
        Task existingTask = getTaskById(taskId);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(existingTask);
    }

    // Delete a task
    public void deleteTask(Long taskId) {
        Task existingTask = getTaskById(taskId);
        taskRepository.delete(existingTask);
    }
}

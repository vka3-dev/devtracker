package com.project_manager.project.controller;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_manager.project.entity.Task;
import com.project_manager.project.services.TaskServices;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServices taskService;

    public TaskController(TaskServices taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTask() {
        return taskService.getAllTasks();
    }
}
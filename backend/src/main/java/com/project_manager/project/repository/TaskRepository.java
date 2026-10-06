package com.project_manager.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project_manager.project.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
package com.project_manager.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project_manager.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
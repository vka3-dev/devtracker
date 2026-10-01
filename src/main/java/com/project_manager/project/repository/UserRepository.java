package com.project_manager.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project_manager.project.entity.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
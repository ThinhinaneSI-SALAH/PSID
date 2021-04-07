package com.project.givemehand.repository;

import com.project.givemehand.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

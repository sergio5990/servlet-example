package com.github.sergio5990.servlet.example.dao.repository;

import com.github.sergio5990.servlet.example.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

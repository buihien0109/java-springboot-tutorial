package com.example.jpaadvenced.criteria_api.repository;

import com.example.jpaadvenced.criteria_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
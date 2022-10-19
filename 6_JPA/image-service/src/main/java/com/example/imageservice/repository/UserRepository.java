package com.example.imageservice.repository;

import com.example.imageservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByNameContainsIgnoreCase(String name);
    Optional<User> findByEmail(String email);
}
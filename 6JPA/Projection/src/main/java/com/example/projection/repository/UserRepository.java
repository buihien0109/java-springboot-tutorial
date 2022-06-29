package com.example.projection.repository;

import com.example.projection.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    <T> List<T> getUsersBy(Class<T> type);

    <T> T getUserById(Integer id, Class<T> type);
}
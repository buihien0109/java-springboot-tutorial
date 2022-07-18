package com.example.projection.repository;

import com.example.projection.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    <T> T getAuthorById(Integer id, Class<T> type);
}
package com.example.dtomapper.repository;

import com.example.dtomapper.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
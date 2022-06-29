package com.example.relationship.repository;

import com.example.relationship.model.many_to_many.unidirection.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
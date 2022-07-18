package com.example.relationship.repository;

import com.example.relationship.model.many_to_many.unidirection.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
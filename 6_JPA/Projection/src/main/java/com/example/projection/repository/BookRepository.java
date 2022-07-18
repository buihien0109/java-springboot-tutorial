package com.example.projection.repository;

import com.example.projection.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    <T> T getBookById(Integer id, Class<T> type);
}
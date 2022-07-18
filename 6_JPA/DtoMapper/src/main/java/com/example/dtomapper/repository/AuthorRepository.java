package com.example.dtomapper.repository;

import com.example.dtomapper.dto.AuthorDto;
import com.example.dtomapper.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("select a.id, a.email, a.name from Author a where a.name = :name")
    List<AuthorDto> findAuthorByName(@Param("name") String name);

    List<Author> findAuthorByEmail(String name);
}
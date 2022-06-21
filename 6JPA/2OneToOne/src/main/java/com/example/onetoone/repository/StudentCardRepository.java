package com.example.onetoone.repository;

import com.example.onetoone.model.StudentCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCardRepository extends JpaRepository<StudentCard, Integer> {
}
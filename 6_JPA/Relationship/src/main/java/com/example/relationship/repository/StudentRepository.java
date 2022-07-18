package com.example.relationship.repository;

import com.example.relationship.model.one_to_one.bidrection.Student;
import com.example.relationship.model.one_to_one.unidirection.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByName(String name);
}
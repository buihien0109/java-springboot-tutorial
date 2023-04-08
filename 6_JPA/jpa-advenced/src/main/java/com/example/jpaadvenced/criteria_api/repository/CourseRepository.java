package com.example.jpaadvenced.criteria_api.repository;

import com.example.jpaadvenced.criteria_api.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
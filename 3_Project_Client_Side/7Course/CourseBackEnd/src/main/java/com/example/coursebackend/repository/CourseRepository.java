package com.example.coursebackend.repository;

import com.example.coursebackend.database.FakeDB;
import com.example.coursebackend.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {
    public List<Course> getAll() {
        return FakeDB.courses;
    }

    public List<Course> findByType(String type) {
        return FakeDB.courses
                .stream()
                .filter(course -> course.getType().equals(type))
                .collect(Collectors.toList());
    }

    public Optional<Course> findById(int id) {
        return FakeDB.courses
                .stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }
}

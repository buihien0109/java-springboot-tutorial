package com.example.coursebackend.service;

import com.example.coursebackend.exception.NotFoundException;
import com.example.coursebackend.model.Course;
import com.example.coursebackend.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    // Lấy danh sách khóa học
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    // Lấy thông tin khóa học theo id
    public Course getCourseById(int id) {
        Optional<Course> courseOptional = courseRepository.getCourseById(id);
        if(courseOptional.isEmpty()) {
            throw new NotFoundException("Không tìm thấy khóa học có id = " + id);
        }
        return courseOptional.get();
    }
}

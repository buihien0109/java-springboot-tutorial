package com.example.coursebackend.service;


import com.example.coursebackend.exception.NotFoundException;
import com.example.coursebackend.model.Course;
import com.example.coursebackend.repository.CourseRepository;
import com.example.coursebackend.request.CreateCourseRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CourseAdminService {
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

    // Tạo khóa học mới
    public Course createCourse(CreateCourseRequest request) {
        Random rd = new Random();

        Course course = Course.builder()
                .id(rd.nextInt(100))
                .title(request.getTitle())
                .description(request.getDescription())
                .type(request.getType())
                .topics(request.getTopics())
                .price(request.getPrice())
                .supporterId(request.getSupporterId())
                .build();

        courseRepository.save(course);
        return course;
    }
}

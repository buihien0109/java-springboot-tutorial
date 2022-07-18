package com.example.coursebackend.controller;

import com.example.coursebackend.model.Course;
import com.example.coursebackend.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("api/v1")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }
}

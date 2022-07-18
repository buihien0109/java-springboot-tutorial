package com.example.coursebackend.controller;

import com.example.coursebackend.model.Course;
import com.example.coursebackend.request.CreateCourseRequest;
import com.example.coursebackend.service.CourseAdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/admin/api/v1")
@AllArgsConstructor
public class CourseAdminController {

    private final CourseAdminService courseAdminService;

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseAdminService.getCourses();
    }

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseAdminService.getCourseById(id);
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody CreateCourseRequest request) {
        return courseAdminService.createCourse(request);
    }

}

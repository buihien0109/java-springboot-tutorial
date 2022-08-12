package com.example.coursebackend.controller;

import com.example.coursebackend.model.Course;
import com.example.coursebackend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getCourses(@RequestParam Optional<String> topic, @RequestParam Optional<String> title) {
        return courseService.getCourses(null, topic, title);
    }

    @GetMapping("/courses/online")
    public List<Course> getCoursesOnline(@RequestParam Optional<String> topic, @RequestParam Optional<String> title) {
        return courseService.getCourses("online", topic, title);
    }

    @GetMapping("/courses/onlab")
    public List<Course> getCoursesOnlab(@RequestParam Optional<String> topic, @RequestParam Optional<String> title) {
        return courseService.getCourses("onlab", topic, title);
    }

    @GetMapping("/courses/{id}")
    public Map<String, Object> getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }
}

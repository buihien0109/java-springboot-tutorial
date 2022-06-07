package com.example.coursebackend.controller;

import com.example.coursebackend.model.Course;
import com.example.coursebackend.request.FilterCourseRequest;
import com.example.coursebackend.request.SearchCourseRequest;
import com.example.coursebackend.response.CourseDetailResponse;
import com.example.coursebackend.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getCourses(@RequestParam(required = false) Optional<String> type) {
        if(type.isPresent()) {
            return courseService.getCourses(type.get());
        }
        return courseService.getCourses();
    }

    @PostMapping("/courses/search")
    public List<Course> searchCourse(@RequestBody SearchCourseRequest request) {
        return courseService.findByName(request);
    }

    @PostMapping("/courses/filter")
    public List<Course> filterCourse(@RequestBody FilterCourseRequest request) {
        return courseService.findByTopic(request);
    }

    @GetMapping("/courses/{id}")
    public CourseDetailResponse getCourseById(@PathVariable int id) {
        return courseService.findById(id);
    }
}

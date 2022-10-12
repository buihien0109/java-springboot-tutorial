package vn.techmaster.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.example.entity.Course;
import vn.techmaster.example.entity.Student;
import vn.techmaster.example.repository.CourseRepository;
import vn.techmaster.example.repository.StudentRepository;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/students")
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("/courses")
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }
}

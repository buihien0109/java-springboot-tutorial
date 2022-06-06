package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.request.CreateStudentRequest;
import com.example.studentmanagement.request.UpdateStudentRequest;
import com.example.studentmanagement.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping("")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/search")
    public List<Student> getStudentById(@RequestParam String name) {
        return studentService.searchByName(name);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createUser(@RequestBody CreateStudentRequest request) {
        return studentService.createStudent(request);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody UpdateStudentRequest request, @PathVariable int id) {
        return studentService.updateStudent(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }
}

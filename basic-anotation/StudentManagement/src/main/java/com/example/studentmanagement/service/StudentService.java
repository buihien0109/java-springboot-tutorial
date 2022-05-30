package com.example.studentmanagement.service;

import com.example.studentmanagement.exception.NotFoundException;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.request.CreateStudentRequest;
import com.example.studentmanagement.request.UpdateStudentRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    // Khởi tạo danh sách học viên để quản lý
    private List<Student> students;

    // Khi class được khởi tạo thì chạy method để thêm học viên vào danh sách
    public StudentService() {
        init();
    }

    // Thêm 1 số đối tượng học viên vào danh sách
    public void init() {
        students = new ArrayList<>();
        students.add(new Student(1, "Bùi Hiên", "hien@gmail.com"));
        students.add(new Student(2, "Thu Hằng", "hang@gmail.com"));
        students.add(new Student(3, "Minh Anh", "anh@gmail.com"));
        students.add(new Student(4, "Minh Duy", "duy@gmail.com"));
    }

    // Lấy danh sách tất cả học viên
    public List<Student> getStudents() {
        return students;
    }

    // Tìm kiếm học viên theo tên
    public List<Student> searchByName(String name) {
        return students
                .stream()
                .filter(student -> student.getName().contains(name))
                .collect(Collectors.toList());
    }

    // Tìm kiếm học viên theo id
    public Student getStudentById(int id) {
        Optional<Student> studentOptional = students
                .stream()
                .filter(student -> student.getId() == id)
                .findFirst();

        if(studentOptional.isEmpty()) {
            throw new NotFoundException("Student with id = " + id + " not exist");
        }

        return studentOptional.get();
    }

    // Tạo học viên mới
    public Student createStudent(CreateStudentRequest request) {
        Random rd = new Random();

        Student student = new Student(rd.nextInt(100), request.getName(), request.getEmail());
        students.add(student);

        return student;
    }

    // Cập nhật thông tin của học viên
    public Student updateStudent(int id, UpdateStudentRequest request) {
        Optional<Student> studentOptional = students
                .stream()
                .filter(student -> student.getId() == id)
                .findFirst();

        if(studentOptional.isEmpty()) {
            throw new NotFoundException("Student with id = " + id + " not exist");
        }

        Student student =  studentOptional.get();
        student.setName(request.getName());

        return student;
    }

    // Xóa học viên theo id
    public void deleteStudent(int id) {
        Optional<Student> studentOptional = students
                .stream()
                .filter(student -> student.getId() == id)
                .findFirst();

        if(studentOptional.isEmpty()) {
            throw new NotFoundException("Student with id = " + id + " not exist");
        }

        students.removeIf(student -> student.getId() == id);
    }
}

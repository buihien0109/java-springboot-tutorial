package com.example.basic.repository;

import com.example.basic.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Lấy danh sách có chứa tên
    List<Student> findStudentByNameContaining(String name);

    // Lấy số lượng có tuổi lớn hơn ?
    long countByAgeGreaterThan(Integer age);

    // Kiểm tra tuổi có = ? hay không
    boolean existsByAge(Integer age);

    // Lấy danh sách học viên có điểm nằm trong khoảng
    List<Student> findByPointBetween(Double pointStart, Double pointEnd);
}
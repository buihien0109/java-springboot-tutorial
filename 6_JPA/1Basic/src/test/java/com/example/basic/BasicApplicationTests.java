package com.example.basic;

import com.example.basic.model.Student;
import com.example.basic.repository.StudentRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BasicApplicationTests {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void test_getStudentByName() {
        List<Student> students = studentRepository.findStudentByNameContaining("Van");
        students.forEach(System.out::println);
    }

    @Test
    public void test_countByAgeGreaterThan() {
        long count = studentRepository.countByAgeGreaterThan(22);
        System.out.println(count);
    }

    @Test
    public void test_existsByAge() {
        boolean isExist = studentRepository.existsByAge(22);
        System.out.println(isExist);
    }

    @Test
    public void test_findByPointBetween() {
        List<Student> students = studentRepository.findByPointBetween(9.0, 10.0);
        students.forEach(System.out::println);
    }
}

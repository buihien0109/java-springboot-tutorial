package com.example.jpanew;

import com.example.jpanew.createId.Student;
import com.example.jpanew.createId.StudentRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentTests {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Rollback(value = false)
    void save_student() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Student student = new Student(faker.name().fullName());
            studentRepository.save(student);
        }
    }
}

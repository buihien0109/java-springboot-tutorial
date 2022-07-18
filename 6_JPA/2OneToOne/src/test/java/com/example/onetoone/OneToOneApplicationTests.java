package com.example.onetoone;

import com.example.onetoone.model.Student;
import com.example.onetoone.model.StudentCard;
import com.example.onetoone.repository.StudentCardRepository;
import com.example.onetoone.repository.StudentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class OneToOneApplicationTests {


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCardRepository studentCardRepository;


    @Test
    void test_getStudentById() {
        Optional<Student> studentOptional = studentRepository.findById(1);
        if(studentOptional.isPresent()) {
            Student student = studentOptional.get();
            System.out.println(student.printInfo());

            System.out.println(student.getStudentCard().getCode());
        }
    }

    @Test
    void test_getStudentCardById() {
        Optional<StudentCard> studentCardOptional = studentCardRepository.findById(1);
        if(studentCardOptional.isPresent()) {
            StudentCard studentCard = studentCardOptional.get();
            System.out.println(studentCard.printInfo());

            System.out.println(studentCard.getStudent().getName());
        }
    }
}

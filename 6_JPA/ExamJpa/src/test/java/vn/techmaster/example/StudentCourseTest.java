package vn.techmaster.example;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import vn.techmaster.example.entity.Course;
import vn.techmaster.example.entity.Student;
import vn.techmaster.example.entity.StudentCourse;
import vn.techmaster.example.repository.CourseRepository;
import vn.techmaster.example.repository.StudentCourseRepository;
import vn.techmaster.example.repository.StudentRepository;

import java.util.List;
import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentCourseTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private Faker faker;

    @Autowired
    private Random rd;

    @Test
    @Rollback(value = false)
    void save_student() {
        for (int i = 0; i < 5; i++) {
            Student student = new Student(faker.name().fullName());
            studentRepository.save(student);
        }
    }

    @Test
    @Rollback(value = false)
    void save_course() {
        for (int i = 0; i < 3; i++) {
            Course course = new Course(faker.leagueOfLegends().champion());
            courseRepository.save(course);
        }
    }

    @Test
    @Rollback(value = false)
    void save_student_course() {
        List<Student> students = studentRepository.findAll();
        List<Course> courses = courseRepository.findAll();

        for (int i = 0; i < 10; i++) {
            Student rdStudent = students.get(rd.nextInt(students.size()));
            Course rdCourse = courses.get(rd.nextInt(courses.size()));
            Integer rdScore = rd.nextInt(10);

            StudentCourse studentCourse = new StudentCourse(rdStudent, rdCourse, rdScore);
            studentCourseRepository.save(studentCourse);
        }
    }

    @Test
    void get_all_course() {
        List<Course> courses = courseRepository.findAll();
        courses.forEach(System.out::println);
    }

    @Test
    void get_all_student() {
        List<Student> students = studentRepository.findAll();
        students.forEach(System.out::println);
    }

    @Test
    void calculate_avg_score() {
        Double rs = studentCourseRepository.calculateAvgScore(3L);
        System.out.println(rs);
    }
}

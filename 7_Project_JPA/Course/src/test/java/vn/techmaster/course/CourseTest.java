package vn.techmaster.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import vn.techmaster.course.entity.Course;
import vn.techmaster.course.repository.CourseRepository;
import vn.techmaster.course.service.CourseService;

@SpringBootTest
public class CourseTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    // Phân trang
    @Test
    void pagination_course_admin_test() {
        Page<Course> page = courseRepository.findAll(PageRequest.of(0, 10));
        System.out.println(page);
    }
}

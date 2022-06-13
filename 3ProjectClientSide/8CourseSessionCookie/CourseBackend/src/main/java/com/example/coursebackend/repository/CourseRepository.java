package com.example.coursebackend.repository;

import com.example.coursebackend.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    private List<Course> courses;

    public CourseRepository() {
        init();
    }

    public void init() {
        courses = new ArrayList<>();
        courses.add(new Course(1, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "onlab", "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg", 4.5, new String[]{"Backend"}, 1_000_000, 3));
        courses.add(new Course(2, "Lập trình iOS Swift căn bản cập nhật 2022", "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay", "onlab", "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/bjxnxQi0", 4.7, new String[]{"Di động"}, 2_000_000, 1));
        courses.add(new Course(3, "Khoá học Lập trình Arduino Scratch cho trẻ em", "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.", "onlab", "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/bt3miv451co41h2qcnr0", 4.3, new String[]{"Database"}, 2_500_000, 2));
        courses.add(new Course(4, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "onlab", "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg", 4.5, new String[]{"Backend"}, 1_000_000,3));
        courses.add(new Course(5, "Lập trình iOS Swift căn bản cập nhật 2022", "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay", "onlab", "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/bjxnxQi0", 4.7, new String[]{"Di động"}, 2_000_000, 1));
        courses.add(new Course(6, "Khoá học Lập trình Arduino Scratch cho trẻ em", "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.", "onlab", "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/bt3miv451co41h2qcnr0", 4.3, new String[]{"Database"}, 5_000_000, 2));
        courses.add(new Course(7, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "online", "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg", 4.5, new String[]{"Backend"}, 3_000_000, 3));
        courses.add(new Course(8, "Lập trình iOS Swift căn bản cập nhật 2022", "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay", "online", "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/bjxnxQi0", 4.7, new String[]{"Di động"}, 8_000_000, 1));
        courses.add(new Course(9, "Khoá học Lập trình Arduino Scratch cho trẻ em", "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.", "online", "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/bt3miv451co41h2qcnr0", 4.3, new String[]{"Database"}, 6_000_000, 2));
        courses.add(new Course(10, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "online", "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg", 4.5, new String[]{"Backend"}, 4_000_000, 3));
    }

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> getCourseById(int id) {
        return courses.stream().filter(course -> course.getId() == id).findFirst();
    }

    public void save(Course course) {
        courses.add(course);
    }
}

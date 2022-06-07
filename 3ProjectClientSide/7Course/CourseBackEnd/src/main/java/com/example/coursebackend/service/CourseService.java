package com.example.coursebackend.service;

import com.example.coursebackend.exception.BadRequestException;
import com.example.coursebackend.exception.NotFoundException;
import com.example.coursebackend.mapper.CourseMapper;
import com.example.coursebackend.model.Course;
import com.example.coursebackend.request.FilterCourseRequest;
import com.example.coursebackend.request.SearchCourseRequest;
import com.example.coursebackend.response.CourseDetailResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private List<Course> courses;

    private final CourseMapper courseMapper;

    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
        init();
    }

    public void init() {
        courses = new ArrayList<>();
        courses.add(new Course(1, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "onlab", "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg", 4.5, new String[]{"Backend"}, 3));
        courses.add(new Course(2, "Lập trình iOS Swift căn bản cập nhật 2022", "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay", "onlab", "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/bjxnxQi0", 4.7, new String[]{"Di động"}, 1));
        courses.add(new Course(3, "Khoá học Lập trình Arduino Scratch cho trẻ em", "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.", "onlab", "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/bt3miv451co41h2qcnr0", 4.3, new String[]{"Database"}, 2));
        courses.add(new Course(4, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "onlab", "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg", 4.5, new String[]{"Backend"}, 3));
        courses.add(new Course(5, "Lập trình iOS Swift căn bản cập nhật 2022", "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay", "onlab", "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/bjxnxQi0", 4.7, new String[]{"Di động"}, 1));
        courses.add(new Course(6, "Khoá học Lập trình Arduino Scratch cho trẻ em", "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.", "onlab", "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/bt3miv451co41h2qcnr0", 4.3, new String[]{"Database"}, 2));
        courses.add(new Course(7, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "online", "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg", 4.5, new String[]{"Backend"}, 3));
        courses.add(new Course(8, "Lập trình iOS Swift căn bản cập nhật 2022", "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay", "online", "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/bjxnxQi0", 4.7, new String[]{"Di động"}, 1));
        courses.add(new Course(9, "Khoá học Lập trình Arduino Scratch cho trẻ em", "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.", "online", "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/bt3miv451co41h2qcnr0", 4.3, new String[]{"Database"}, 2));
        courses.add(new Course(10, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "online", "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg", 4.5, new String[]{"Backend"}, 3));
    }

    // Lấy danh sách tất cả khóa học
    public List<Course> getCourses() {
        return courses;
    }

    // Lấy danh sách khóa học theo type (online - onlab)
    public List<Course> getCourses(String type) {
        return switch (type) {
            case "onlab" -> courses.stream().filter(course -> course.getType().contains("onlab")).collect(Collectors.toList());
            case "online" -> courses.stream().filter(course -> course.getType().contains("online")).collect(Collectors.toList());
            default -> throw new BadRequestException("Type không hợp lệ");
        };
    }

    // Tìm kiếm khóa học theo chủ đề
    public List<Course> findByTopic(FilterCourseRequest request) {
        // Lọc ra các khóa học theo type;
        List<Course> coursesFilter = new ArrayList<>();
        if (request.getType() == null) {
            coursesFilter = getCourses();
        } else {
            coursesFilter = getCourses(request.getType());
        }

        // Lọc ra các khóa học theo chủ đề
        return coursesFilter
                .stream()
                .filter(course -> Arrays.asList(course.getTopics()).contains(request.getTopic()))
                .collect(Collectors.toList());
    }

    // Tìm kiếm khóa học theo tên
    public List<Course> findByName(SearchCourseRequest request) {
        // Lọc ra các khóa học theo type;
        List<Course> coursesFilter = new ArrayList<>();
        if (request.getType() == null) {
            coursesFilter = getCourses();
        } else {
            coursesFilter = getCourses(request.getType());
        }

        // Lọc ra các khóa học theo chủ đề
        return coursesFilter
                .stream()
                .filter(course -> course.getTitle().toLowerCase().contains(request.getTerm().toLowerCase()))
                .collect(Collectors.toList());
    }

    // Lấy chi tiết thông tin khóa học
    public CourseDetailResponse findById(int id) {
        Optional<Course> courseOptional =  courses
                .stream()
                .filter(course -> course.getId() == id)
                .findFirst();

        if(courseOptional.isPresent()) {
            return courseMapper.toCourseDetail(courseOptional.get());
        }

        throw new NotFoundException("Không tìm thấy khóa học có id = " + id);
    }
}

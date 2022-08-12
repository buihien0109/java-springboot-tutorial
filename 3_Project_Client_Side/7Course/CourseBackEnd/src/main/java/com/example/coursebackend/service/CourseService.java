package com.example.coursebackend.service;

import com.example.coursebackend.exception.NotFoundException;
import com.example.coursebackend.model.Course;
import com.example.coursebackend.model.Supporter;
import com.example.coursebackend.repository.CourseRepository;
import com.example.coursebackend.repository.SupporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SupporterRepository supporterRepository;

    // Lấy danh sách khóa học (tất cả - online - onlab)
    public List<Course> getCourses(String type, Optional<String> topic, Optional<String> title) {
        if (type == null) {
            return getAllCourses(topic, title);
        }

        return getCoursesByType(type, topic, title);
    }

    // Lấy danh sách khóa học (tất cả )
    private List<Course> getAllCourses(Optional<String> topic, Optional<String> title) {
        List<Course> courses = courseRepository.getAll();
        return filterCourse(topic, title, courses);
    }

    // Lấy danh sách khóa học (online - onlab)
    private List<Course> getCoursesByType(String type, Optional<String> topic, Optional<String> title) {
        List<Course> courses = courseRepository.findByType(type);
        return filterCourse(topic, title, courses);
    }

    // Lọc khóa học
    private List<Course> filterCourse(Optional<String> topic, Optional<String> title, List<Course> courses) {
        if (topic.isPresent()) {
            courses = courses
                    .stream()
                    .filter(course -> course.getTopics().contains(topic.get()))
                    .collect(Collectors.toList());
        }

        if (title.isPresent()) {
            courses = courses
                    .stream()
                    .filter(course -> course.getTitle().toLowerCase().contains(title.get().toLowerCase()))
                    .collect(Collectors.toList());
        }
        return courses;
    }

    // Chi tiết khóa học
    public Map<String, Object> getCourseById(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not find course with id = " + id);
        });

        Supporter supporter = supporterRepository.findById(course.getSupporterId()).orElseThrow(() -> {
            throw new NotFoundException("Not find supporter with id = " + course.getSupporterId());
        });

        Map<String, Object> rs = new HashMap<>();
        rs.put("course", course);
        rs.put("supporter", supporter);

        return rs;
    }
}

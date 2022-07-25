package vn.techmaster.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.course.entity.Course;
import vn.techmaster.course.repository.CourseRepository;
import vn.techmaster.course.request.CreateCourseRequest;
import vn.techmaster.course.request.UpdateCourseRequest;
import vn.techmaster.course.service.CourseService;
import vn.techmaster.course.service.TopicService;
import vn.techmaster.course.service.UserService;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseRepository courseRepository;

    // Danh sách View
    @GetMapping("/admin/courses")
    public String getListCoursePage(Model model,
                                    @RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "10") int size) {
        model.addAttribute("data", courseService.getAll(page, size));
        return "admin/course-list";
    }

    @GetMapping("/admin/courses/create")
    public String getCreateCoursePage(Model model) {
        model.addAttribute("topics", topicService.getAll());
        model.addAttribute("supporters", userService.getAll());
        return "admin/course-create";
    }

    @GetMapping("/admin/courses/{id}/{slug}")
    public String getDetailCoursePage(@PathVariable Integer id, @PathVariable String slug, Model model) {
        model.addAttribute("topics", topicService.getAll());
        model.addAttribute("supporters", userService.getAll());
        model.addAttribute("course", courseService.getCourseById(id));
        return "admin/course-edit";
    }

    // Danh sách API
    @PostMapping("/api/admin/courses")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseRequest request) {
        Course course = courseService.createCourse(request);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PutMapping("/api/admin/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id, @RequestBody UpdateCourseRequest request) {
        Course course = courseService.updateCourse(id, request);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/api/admin/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/admin/courses/{id}/upload-file")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file, @PathVariable Integer id) {
        String filePath = courseService.uploadFile(id, file);
        return ResponseEntity.ok(filePath);
    }

    @GetMapping("/api/courses")
    public ResponseEntity<?> getCourses(@RequestParam(required = false, defaultValue = "1") int page) {
        return ResponseEntity.ok(courseRepository.findAll(PageRequest.of(page - 1, 10)));
    }
}

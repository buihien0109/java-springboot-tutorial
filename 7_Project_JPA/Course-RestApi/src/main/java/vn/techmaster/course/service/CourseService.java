package vn.techmaster.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.course.entity.Course;
import vn.techmaster.course.entity.Topic;
import vn.techmaster.course.entity.User;
import vn.techmaster.course.exception.NotFoundException;
import vn.techmaster.course.repository.CourseRepository;
import vn.techmaster.course.repository.TopicRepository;
import vn.techmaster.course.repository.UserRepository;
import vn.techmaster.course.request.UpsertCourseRequest;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final ImageService fileService;

    // Lấy danh sách tất cả khóa học
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    // Lấy thông tin khóa học theo id
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found course with id = " + id);
        });
    }

    // Tạo khóa học
    public Course createCourse(UpsertCourseRequest request) {
        // Tìm kiếm user
        User user = userRepository.getUserById(request.getUserId());

        // Tìm kiếm danh sách topic
        Set<Topic> topics = topicRepository.getTopicsByIdIn(request.getTopicsId());

        Course course = Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .topics(topics)
                .user(user)
                .build();

        return courseRepository.save(course);
    }

    // Cập nhật thông tin khóa học
    public Course updateCourse(Integer id, UpsertCourseRequest request) {
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found course with id = " + id);
        });

        // Tìm kiếm user
        User user = userRepository.getUserById(request.getUserId());

        // Tìm kiếm danh sách topic
        Set<Topic> topics = topicRepository.getTopicsByIdIn(request.getTopicsId());

        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setType(request.getType());
        course.setUser(user);
        course.setTopics(topics);

        return courseRepository.save(course);
    }

    // Xóa khóa học
    public void deleteCourse(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found course with id = " + id);
        });

        courseRepository.delete(course);
    }

    // Thay đổi ảnh khóa học
    public String uploadFile(Integer id, MultipartFile file) {
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found course with id = " + id);
        });

        // Upload file
        String filePath = fileService.uploadFile(file);

        course.setThumbnail(filePath);
        courseRepository.save(course);

        return filePath;
    }
}

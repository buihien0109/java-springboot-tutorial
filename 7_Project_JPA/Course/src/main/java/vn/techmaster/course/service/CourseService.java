package vn.techmaster.course.service;

import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.course.entity.Course;
import vn.techmaster.course.entity.Topic;
import vn.techmaster.course.entity.User;
import vn.techmaster.course.exception.NotFoundException;
import vn.techmaster.course.repository.CourseRepository;
import vn.techmaster.course.repository.TopicRepository;
import vn.techmaster.course.repository.UserRepository;
import vn.techmaster.course.request.CreateCourseRequest;
import vn.techmaster.course.request.UpdateCourseRequest;
import vn.techmaster.course.util.PageUtil;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private Slugify slugify;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private FileService fileService;

    // Lấy danh sách tất cả khóa học
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    // Lấy danh sách tất cả khóa học có phân trang
    public PageUtil<Course> getAll(int page, int size) {
        Page<Course> pageInfo = courseRepository.findAll(PageRequest.of(page - 1, size));

        return new PageUtil<>(
                pageInfo.getContent(),
                pageInfo.getPageable().getPageNumber() + 1,
                pageInfo.getPageable().getPageSize(),
                IntStream.range(1, pageInfo.getTotalPages() + 1).boxed().toList(),
                pageInfo.getTotalElements(),
                pageInfo.isFirst(),
                pageInfo.isLast()
        );
    }

    // Lấy danh sách tất cả khóa học theo bộ lọc (topic, name)
    public List<Course> getAll(String topic, String name) {
        List<Course> courses = courseRepository.findAll();
        return getCoursesFilter(topic, name, courses);
    }

    // Lấy danh sách khóa học theo type theo bộ lọc (topic, name)
    public List<Course> getCoursesByType(String type, String topic, String name) {
        List<Course> courses = courseRepository.getCourseByType(type);

        return getCoursesFilter(topic, name, courses);
    }

    // Lọc danh sách khóa học theo type, topic, name
    private List<Course> getCoursesFilter(String topic, String name, List<Course> courses) {
        if(!topic.trim().equals("")) {
            Topic topicObj = topicRepository.getTopicsByNameContaining(topic);
            courses = courses
                    .stream()
                    .filter(course -> course.getTopics().contains(topicObj))
                    .collect(Collectors.toList());
        }
        if(!name.trim().equals("")) {
            courses = courses
                    .stream()
                    .filter(course -> course.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return courses;
    }

    // Lấy thông tin khóa học theo id
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found course with id = " + id);
        });
    }

    // Tạo khóa học
    public Course createCourse(CreateCourseRequest request) {
        // Tìm kiếm user
        User user = userRepository.getUserById(request.getSupporterId());

        // Tìm kiếm danh sách topic
        Set<Topic> topics = topicRepository.getTopicsByIdIn(request.getTopics());

        Course course = Course.builder()
                .name(request.getName())
                .slug(slugify.slugify(request.getName()))
                .description(request.getDescription())
                .type(request.getType())
                .topics(topics)
                .user(user)
                .build();

        courseRepository.save(course);
        return course;
    }

    // Cập nhật thông tin khóa học
    public Course updateCourse(int id, UpdateCourseRequest request) {
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found course with id = " + id);
        });

        // Tìm kiếm user
        User user = userRepository.getUserById(request.getSupporterId());

        // Tìm kiếm danh sách topic
        Set<Topic> topics = topicRepository.getTopicsByIdIn(request.getTopics());

        course.setName(request.getName());
        course.setSlug(slugify.slugify(request.getName()));
        course.setDescription(request.getDescription());
        course.setType(request.getType());
        course.setUser(user);
        course.setTopics(topics);

        courseRepository.save(course);
        return course;
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
        Course course = getCourseById(id);

        // Upload file
        String filePath = fileService.uploadFile(file);

        course.setThumbnail(filePath);
        courseRepository.save(course);

        return filePath;
    }
}

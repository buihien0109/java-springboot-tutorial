package vn.techmaster.course;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.course.entity.Course;
import vn.techmaster.course.entity.Image;
import vn.techmaster.course.entity.Topic;
import vn.techmaster.course.entity.User;
import vn.techmaster.course.repository.CourseRepository;
import vn.techmaster.course.repository.ImageRepository;
import vn.techmaster.course.repository.TopicRepository;
import vn.techmaster.course.repository.UserRepository;

import java.util.*;

@SpringBootTest
class InitDataTest {
    @Autowired
    private Faker faker;

    @Autowired
    private Slugify slugify;

    @Autowired
    private Random random;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TopicRepository topicRepository;

//    @Test
//    void save_user() {
//        User user = User.builder()
//                .name("Phạm Thị Mẫn")
//                .email("manpham@techmaster.vn")
//                .phone("0963023185")
//                .avatar("https://media.techmaster.vn/api/static/crop/bv9jp4k51co7nj2mhht0")
//                .build();
//
//        User user1 = User.builder()
//                .name("Dương Đức Thịnh")
//                .email("thinh@techmaster.vn")
//                .phone("0987273764")
//                .avatar("https://media.techmaster.vn/api/static/c2m5ou451cob24f6skeg/ccjlg0NC")
//                .build();
//
//        User user2 = User.builder()
//                .name("Nguyễn Thanh Hương")
//                .email("huong@techmaster.vn")
//                .phone("0382416368")
//                .avatar("https://media.techmaster.vn/api/static/crop/brm3huc51co50mv77sag")
//                .build();
//
//        userRepository.saveAll(List.of(user, user1, user2));
//
//    }
//
//    @Test
//    void save_topic() {
//        List<String> topicNames = List.of("AWS", "Devops", "BackEnd", "FrontEnd", "Database", "Mobile");
//        topicNames.forEach(name -> {
//            Topic topic = Topic.builder()
//                    .name(name)
//                    .build();
//
//            topicRepository.save(topic);
//        });
//    }
//
//    @Test
//    void save_image() {
//        for (int i = 0; i < 30; i++) {
//            String genarateFileId = UUID.randomUUID().toString();
//            Image image = Image.builder()
//                    .id(genarateFileId)
//                    .link(faker.company().logo())
//                    .build();
//            imageRepository.save(image);
//        }
//    }
//
//    @Test
//    void save_course() {
//        // Lấy danh sách image
//        List<Image> images = imageRepository.findAll();
//
//        // Lấy danh sách user
//        List<User> users = userRepository.findAll();
//
//        // Lấy danh sách topic
//        List<Topic> topics = topicRepository.findAll();
//
//        for (int i = 0; i < 12; i++) {
//            String name = faker.company().name();
//
//            // Random image
//            String imageRd = images.get(random.nextInt(images.size())).getLink();
//
//            // Random user
//            User userRd = users.get(random.nextInt(users.size()));
//
//            // Danh sách topic
//            Set<Topic> topicsRd = new LinkedHashSet<>();
//
//            for (int j = 0; j < 3; j++) {
//                Topic topicRd = topics.get(random.nextInt(topics.size()));
//                topicsRd.add(topicRd);
//            }
//
//            Course course = Course.builder()
//                    .name(name)
//                    .slug(slugify.slugify(name))
//                    .type(random.nextInt(2) == 1 ? "onlab" : "online")
//                    .description(faker.lorem().sentence(100))
//                    .thumbnail(imageRd)
//                    .user(userRd)
//                    .topics(topicsRd)
//                    .build();
//            courseRepository.save(course);
//        }
//    }
}

package vn.techmaster.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blog.entity.Image;
import vn.techmaster.blog.entity.User;
import vn.techmaster.blog.projection.UserInfo;
import vn.techmaster.blog.repository.BlogRepository;
import vn.techmaster.blog.repository.ImageRepository;
import vn.techmaster.blog.repository.UserRepository;

import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private BlogRepository blogRepository;


    @Test
    void get_user_info_by_id_test() {
        UserInfo userInfo = userRepository.getUserById(21, UserInfo.class);
        userInfo.showInfo();

        User user = userRepository.getUserById(21, User.class);
        System.out.println(user);
    }

    @Test
    void get_images_by_user_id() {
        List<Image> images = imageRepository.getImagesByUserId(21);
        images.forEach(System.out::println);
    }
}

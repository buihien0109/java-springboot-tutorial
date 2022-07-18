package vn.techmaster.jpablog;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.jpablog.entity.*;
import vn.techmaster.jpablog.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class InitDataTest {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IdentityCardRepository identityCardRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Faker faker;

    @Autowired
    private Slugify slugify;

    @Test
    void save_user_identity_card() {
        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .password(faker.number().digits(3))
                    .identityCard(new IdentityCard())
                    .build();

            userRepository.save(user);
        }
    }

    @Test
    void save_category_test() {
        for (int i = 0; i < 10 ; i++) {
            Category category = Category.builder()
                    .name(faker.leagueOfLegends().champion())
                    .build();

            categoryRepository.save(category);
        }
    }

    @Test
    void save_image() {
        Random rd = new Random();

        // Lấy danh sách user
        List<User> users = userRepository.findAll();

        // Tạo danh sách ảnh
        for (int i = 0; i < 150 ; i++) {
            // Lấy ngẫu nhiên user
            User userRd = users.get(rd.nextInt(users.size()));

            Image image = Image.builder()
                    .url(faker.internet().image())
                    .user(userRd)
                    .build();

            imageRepository.save(image);
        }
    }

    @Test
    void getImagesByUserIdTest() {
        List<Image> images = imageRepository.getImagesByUserId(1);
        images.forEach(System.out::println);
    }

    @Test
    void save_blog() {
        Random rd = new Random();

        // Lấy danh sách user
        List<User> users = userRepository.findAll();

        // Lấy danh sách category
        List<Category> categories = categoryRepository.findAll();

        // Tạo danh sách blog
        for (int i = 0; i < 30 ; i++) {
            // Lấy ngẫu nhiên user
            User userRd = users.get(rd.nextInt(users.size()));

            // Lấy danh sách ảnh của userId
            List<Image> images = imageRepository.getImagesByUserId(userRd.getId());

            // Lấy ngẫu nhiên image của user;
            String imageRd = images.get(rd.nextInt(images.size())).getUrl();

            // Lấy ngẫu nhiên category
            List<Category> categoriesRd = new ArrayList<>();
            for (int j = 0; j < 5 ; j++) {
                Category categoryRd = categories.get(rd.nextInt(categories.size()));
                if(!categoriesRd.contains(categoryRd)) {
                    categoriesRd.add(categoryRd);
                }
            }

            // Tạo blog
            String title = faker.lorem().sentence(10);
            Blog blog = Blog.builder()
                    .title(i + " " + title)
                    .slug(slugify.slugify(title))
                    .description(i + " " + faker.lorem().sentence(30))
                    .content(i + " " + faker.lorem().sentence(500))
                    .thumbnail(imageRd)
                    .categories(categoriesRd)
                    .status(rd.nextInt(2))
                    .user(userRd)
                    .build();

            blogRepository.save(blog);
        }
    }

    @Test
    void save_comment() {
        Random rd = new Random();

        // Lấy danh sách user
        List<User> users = userRepository.findAll();

        // Lấy danh sách blog
        List<Blog> blogs = blogRepository.findAll();

        // Tạo danh sách comment
        for (int i = 0; i < 100 ; i++) {
            // Lấy ngẫu nhiên user
            User userRd = users.get(rd.nextInt(users.size()));

            // Lấy ngẫu nhiên user
            Blog blogRd = blogs.get(rd.nextInt(blogs.size()));

            Comment comment = Comment.builder()
                    .content(faker.lorem().sentence(20))
                    .user(userRd)
                    .blog(blogRd)
                    .build();

            commentRepository.save(comment);
        }
    }
}

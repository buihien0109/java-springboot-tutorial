package vn.techmaster.blog;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blog.entity.*;
import vn.techmaster.blog.repository.*;

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
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Faker faker;

    @Autowired
    private Slugify slugify;

    @Autowired
    private Random rd;

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
    void save_category() {
        for (int i = 0; i < 10; i++) {
            Category category = Category.builder()
                    .name(faker.leagueOfLegends().champion())
                    .build();

            categoryRepository.save(category);
        }
    }

    @Test
    void save_image() {
        // Lấy ds user
        List<User> users = userRepository.findAll();

        for (int i = 0; i < 150; i++) {
            User userRd = users.get(rd.nextInt(users.size()));

            Image image = Image.builder()
                    .url(faker.company().logo())
                    .user(userRd)
                    .build();

            imageRepository.save(image);
        }
    }

    @Test
    void save_avatar_of_user() {
        // Lấy ds user
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            List<Image> images = imageRepository.getImagesByUserId(user.getId());
            String imageRd = images.get(rd.nextInt(images.size())).getUrl();
            user.setAvatar(imageRd);
            userRepository.save(user);
        });
    }

    @Test
    void save_blog() {
        List<User> users = userRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        for (int i = 0; i < 30; i++) {
            User userRd = users.get(rd.nextInt(users.size()));

            List<Image> images = imageRepository.getImagesByUserId(userRd.getId());
            String imageRd = images.get(rd.nextInt(images.size())).getUrl();

            List<Category> categoriesRd = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Category categoryRd = categories.get(rd.nextInt(categories.size()));
                if (!categoriesRd.contains(categoryRd)) {
                    categoriesRd.add(categoryRd);
                }
            }

            String title = faker.lorem().sentence(10);
            Blog blog = Blog.builder()
                    .title(title)
                    .slug(slugify.slugify(title))
                    .description(faker.lorem().sentence(50))
                    .content(faker.lorem().sentence(100))
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
        List<User> users = userRepository.findAll();
        List<Blog> blogs = blogRepository.findAll();

        for (int i = 0; i < 100; i++) {
            User userRd = users.get(rd.nextInt(users.size()));
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

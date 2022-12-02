package com.example.basic;

import com.example.basic.entity.Blog;
import com.example.basic.entity.Category;
import com.example.basic.entity.User;
import com.example.basic.repository.BlogRepository;
import com.example.basic.repository.CategoryRepository;
import com.example.basic.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BasicApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Faker faker;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Random random;

    @Autowired
    private BlogRepository blogRepository;

    @Test
    void add_user() {
        User user = User.builder()
                .name("Bui Hien")
                .email("hien@techmaster.vn")
                .password(passwordEncoder.encode("111"))
                .avatar("https://media.techmaster.vn/api/static/kC46wkzx/LszCg_7K")
                .roles(Arrays.asList("USER", "ADMIN"))
                .build();

        User user1 = User.builder()
                .name("Thu Hang")
                .email("hang@gmail.com")
                .password(passwordEncoder.encode("111"))
                .avatar("https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/ifmqRt74")
                .roles(List.of("USER", "ADMIN"))
                .build();

        userRepository.saveAll(List.of(user, user1));
    }

    @Test
    void save_category() {
        for (int i = 0; i < 5; i++) {
            Category category = Category.builder()
                    .name(faker.leagueOfLegends().champion())
                    .build();
            categoryRepository.save(category);
        }
    }

    @Test
    void save_blog() {
        List<User> users = userRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        for (int i = 0; i < 25; i++) {
            // Random user
            User rdUser = users.get(random.nextInt(users.size()));

            // Random category
            Set<Category> rdCategories = new HashSet<>();
            for (int j = 0; j < 3; j++) {
                Category rdCate = categories.get(random.nextInt(categories.size()));
                rdCategories.add(rdCate);
            }

            Blog blog = Blog.builder()

                    .build();
            blogRepository.save(blog);
        }
    }
}

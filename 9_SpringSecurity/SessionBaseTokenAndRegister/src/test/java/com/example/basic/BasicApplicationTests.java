package com.example.basic;

import com.example.basic.entity.User;
import com.example.basic.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BasicApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void add_user() {
        User user = User.builder()
                .name("Bui Hien")
                .email("hien@techmaster.vn")
                .password(passwordEncoder.encode("111"))
                .avatar("https://media.techmaster.vn/api/static/kC46wkzx/LszCg_7K")
                .role(Arrays.asList("USER", "EDITOR", "ADMIN"))
                .enabled(true)
                .build();

        User user1 = User.builder()
                .name("Thu Hang")
                .email("hang@gmail.com")
                .password(passwordEncoder.encode("111"))
                .avatar("https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/ifmqRt74")
                .role(List.of("USER"))
                .enabled(true)
                .build();

        User user2 = User.builder()
                .name("Minh Duy")
                .email("duy@gmail.com")
                .password(passwordEncoder.encode("111"))
                .avatar("https://media.techmaster.vn/api/static/c2m5ou451cob24f6skeg/3Zhnoo2k")
                .role(Arrays.asList("USER", "EDITOR"))
                .enabled(true)
                .build();

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    void find_by_email() {
        User user = userRepository.findByEmail("hien@techmaster.vn");

        assertThat(user).isNotNull();
    }

    @Test
    void test_save_user() {
        User user = User.builder()
                .name("Nguyễn Văn A")
                .email("a@gmail.com")
                .password("123")
                .role(List.of("USER"))
                .build();

        System.out.println(user);
    }
}

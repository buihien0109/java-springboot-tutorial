package com.example.basic;

import com.example.basic.entity.User;
import com.example.basic.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockHttpSession;
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

    @Bean
    public MockHttpSession mockHttpSession() {
        return new MockHttpSession();
    }

    @Test
    void add_user() {
        User user = User.builder()
                .name("Bui Hien")
                .email("hien@techmaster.vn")
                .password(passwordEncoder.encode("111"))
                .avatar("https://media.techmaster.vn/api/static/kC46wkzx/LszCg_7K")
                .role(Arrays.asList("USER", "ADMIN"))
                .build();

        User user1 = User.builder()
                .name("Thu Hang")
                .email("hang@gmail.com")
                .password(passwordEncoder.encode("111"))
                .avatar("https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/ifmqRt74")
                .role(List.of("USER"))
                .build();

        userRepository.saveAll(List.of(user, user1));
    }
}

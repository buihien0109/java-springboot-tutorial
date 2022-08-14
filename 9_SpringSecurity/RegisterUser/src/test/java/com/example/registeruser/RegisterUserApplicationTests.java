package com.example.registeruser;

import com.example.registeruser.entity.User;
import com.example.registeruser.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RegisterUserApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void save_user_test() {
        User user1 = User.builder()
                .name("Bùi Hiên")
                .email("hien@techmaster.vn")
                .password(passwordEncoder.encode("111"))
                .enabled(true)
                .role(new ArrayList<>(List.of("USER", "ADMIN")))
                .build();

        User user2 = User.builder()
                .name("Thu Hằng")
                .email("14thuhang@gmail.com")
                .password(passwordEncoder.encode("111"))
                .enabled(true)
                .role(new ArrayList<>(List.of("USER")))
                .build();

        User user3 = User.builder()
                .name("Minh Duy")
                .email("a@gmail.com")
                .password(passwordEncoder.encode("111"))
                .enabled(false)
                .role(new ArrayList<>(List.of("USER")))
                .build();

        userRepository.saveAll(List.of(user1, user2, user3));
    }

}

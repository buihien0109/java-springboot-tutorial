package com.example.jwt;

import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
import com.example.jwt.repository.RoleRepository;
import com.example.jwt.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class JwtApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_roles() {
        List<Role> roles = List.of(
                new Role(null, "ADMIN"),
                new Role(null, "USER"),
                new Role(null, "AUTHOR")
        );

        roleRepository.saveAll(roles);
    }

    @Test
    void save_users() {
        Role userRole = roleRepository.findByName("USER").orElse(null);
        Role adminRole = roleRepository.findByName("ADMIN").orElse(null);
        Role authorRole = roleRepository.findByName("AUTHOR").orElse(null);

        List<User> users = List.of(
                new User(null, "Bùi Hiên", "hien@gmail.com", passwordEncoder.encode("111"), List.of(adminRole, userRole)),
                new User(null, "Minh Duy", "duy@gmail.com", passwordEncoder.encode("111"), List.of(userRole)),
                new User(null, "Thu Hằng", "hang@gmail.com", passwordEncoder.encode("111"), List.of(authorRole, userRole))
        );

        userRepository.saveAll(users);
    }
}

package vn.techmaster.jpatutorial.entity_init_test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.jpatutorial.entity_init.User;
import vn.techmaster.jpatutorial.entity_init.UserRepository;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertUserTest() {
        String id = UUID.randomUUID().toString();
        User user = new User(id, "Bùi Hiên", "hien@gmail.com", "0987654321", "1111", "https://via.placeholder.com/250");

        // Lưu vào trong CSDL
        userRepository.save(user);

        User userSave = userRepository.findById(id).get();
        System.out.println(user);

        assertThat(userSave.getId()).isEqualTo(id);
        assertThat(userSave).isNotNull();
    }

    @Test
    public void findByEmailTest() {
        User user = userRepository.findByEmail("hien@gmail.com");
        Assertions.assertThat(user).isNotNull();
    }
}

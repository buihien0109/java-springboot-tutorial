package com.example.imageservice;

import com.example.imageservice.entity.User;
import com.example.imageservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ImageServiceApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = false)
    void save_user() {
        List<User> users = new ArrayList<>();
        users.add(new User(null, "Bùi Hiên", "buihien01091997@gmail.com", "0344005816", "Tỉnh Thái Bình", null, "111"));
        users.add(new User(null, "Nguyễn Thu Hằng", "thuhangvnua@gmail.com", "0123456789", "Tỉnh Nam Định", null, "222"));
        users.add(new User(null, "Bùi Phương Loan", "hien@techmaster.vn", "0123456789", "Tỉnh Hưng Yên", null, "333"));

        userRepository.saveAll(users);
    }

}

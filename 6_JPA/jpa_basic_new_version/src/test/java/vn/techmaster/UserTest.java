package vn.techmaster;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import vn.techmaster.entity.Student;
import vn.techmaster.entity.User;
import vn.techmaster.repository.StudentRepository;
import vn.techmaster.repository.UserRepository;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Rollback(value = false)
    void save_user() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("User " + i);
            userRepository.save(user);
        }
    }

    @Test
    @Rollback(value = false)
    void save_student() {
        for (int i = 0; i < 3; i++) {
            Student std = new Student();
            studentRepository.save(std);
        }
    }
}

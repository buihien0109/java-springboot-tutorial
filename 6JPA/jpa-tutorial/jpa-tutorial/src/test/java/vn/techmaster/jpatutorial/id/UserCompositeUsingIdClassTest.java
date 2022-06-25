package vn.techmaster.jpatutorial.id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.jpatutorial.id.id_composite_id_class.UserCompositeUsingIdClass;
import vn.techmaster.jpatutorial.id.id_composite_id_class.UserCompositeUsingIdClassRepository;
import vn.techmaster.jpatutorial.id.id_composite_id_class.UserId;

import java.util.Optional;

@SpringBootTest
public class UserCompositeUsingIdClassTest {

    @Autowired
    private UserCompositeUsingIdClassRepository repo;

    @Test
    void saveUserTest() {
        // Trường hợp tự tạo id thủ công
        UserCompositeUsingIdClass user = new UserCompositeUsingIdClass(1, "Bùi Hiên");
        repo.save(user);
    }

    @Test
    void getUserTest() {
        UserId userId = new UserId(1, "Bùi Hiên");
        Optional<UserCompositeUsingIdClass> optionalUser = repo.findById(userId);

        if(optionalUser.isPresent()) {
            UserCompositeUsingIdClass user = optionalUser.get();
            System.out.println(user.getId());
            System.out.println(user.getName());
        }
    }

    @Test
    void saveUserOtherTest() {
        // Trường hợp tự generate id
        UserCompositeUsingIdClass user = new UserCompositeUsingIdClass();
        repo.save(user);
    }
}

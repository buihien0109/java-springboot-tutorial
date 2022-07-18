package vn.techmaster.jpatutorial.id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.jpatutorial.id.id_uuid.UserUUID;
import vn.techmaster.jpatutorial.id.id_uuid.UserUUIDRepository;

@SpringBootTest
public class UserUuidTest {

    @Autowired
    private UserUUIDRepository repo;

    @Test
    void saveUserUuidTest() {
        UserUUID userUUID = repo.save(new UserUUID());
        System.out.println(userUUID.getId());
    }
}

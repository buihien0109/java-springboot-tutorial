package vn.techmaster.jpatutorial.id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.jpatutorial.id.id_custom_generate.UserCustomGenerate;
import vn.techmaster.jpatutorial.id.id_custom_generate.UserCustomGenerateRepository;

@SpringBootTest
public class UserCustomGenerateTest {

    @Autowired
    private UserCustomGenerateRepository repo;

    @Test
    void saveUserCustomGenerate() {
        UserCustomGenerate userCustomGenerate = repo.save(new UserCustomGenerate());
        System.out.println(userCustomGenerate.getId());
    }
}

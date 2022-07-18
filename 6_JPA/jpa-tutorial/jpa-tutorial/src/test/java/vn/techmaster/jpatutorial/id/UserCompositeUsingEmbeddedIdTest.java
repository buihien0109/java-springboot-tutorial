package vn.techmaster.jpatutorial.id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.jpatutorial.id.id_composite_embedded_id.UserCompositeUsingEmbeddedId;
import vn.techmaster.jpatutorial.id.id_composite_embedded_id.UserCompositeUsingEmbeddedIdRepository;
import vn.techmaster.jpatutorial.id.id_composite_embedded_id.UserId;

@SpringBootTest
public class UserCompositeUsingEmbeddedIdTest {

    @Autowired
    private UserCompositeUsingEmbeddedIdRepository repo;

    @Test
    void saveUser() {
        UserId userId = new UserId(123, "Bùi Hiên");
        UserCompositeUsingEmbeddedId user = new UserCompositeUsingEmbeddedId(userId);
        repo.save(user);
    }

    @Test
    void getUser() {
        UserId userId = new UserId(123, "Bùi Hiên");
        UserCompositeUsingEmbeddedId user = repo.getReferenceById(userId);

        System.out.println(user.getUserId().getId());
        System.out.println(user.getUserId().getName());
    }
}

package vn.techmaster.jpatutorial.id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.jpatutorial.id.id_identity.UserIdentity;
import vn.techmaster.jpatutorial.id.id_identity.UserIdentityRepository;

@SpringBootTest
public class UserIdentityTest {

    @Autowired
    private UserIdentityRepository repo;

    @Test
    public void saveUserIdentityTest() {
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setName("Bùi Hiên");
        repo.save(userIdentity);
    }

    @Test
    public void saveUserIdentityOtherTest() {
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setName("Phương Loan");
        repo.save(userIdentity);
    }
}

/*
Hibernate:
    insert
    into
        user_identity
        (name)
    values
        (?)

*/

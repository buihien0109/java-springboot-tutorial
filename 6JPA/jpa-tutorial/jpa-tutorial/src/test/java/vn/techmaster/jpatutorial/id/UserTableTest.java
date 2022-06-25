package vn.techmaster.jpatutorial.id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.jpatutorial.id.id_table.UserTable;
import vn.techmaster.jpatutorial.id.id_table.UserTableRepository;

@SpringBootTest
public class UserTableTest {

    @Autowired
    private UserTableRepository repo;

    @Test
    void saveUserTableTest() {
        repo.save(new UserTable());
    }
}

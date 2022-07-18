package vn.techmaster.jpatutorial.id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.jpatutorial.id.id_auto.UserAuto;
import vn.techmaster.jpatutorial.id.id_auto.UserAutoRepository;

@SpringBootTest
public class UserAutoTest {

    @Autowired
    private UserAutoRepository repo;

    @Test
    public void saveUserTest() {
        UserAuto userAuto = new UserAuto();
        userAuto.setName("Bùi Hiên");

        repo.save(userAuto);
    }
}

/*
// Tạo ra bảng hibernate_sequence để lưu trữ id
Hibernate:
    select
        next_val as id_val
    from
        hibernate_sequence for update

Hibernate:
    update
        hibernate_sequence
    set
        next_val= ?
    where
        next_val=?
Hibernate:
    insert
    into
        user_auto
        (name, id)
    values
        (?, ?)
 */

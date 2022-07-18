package vn.techmaster.jpatutorial.id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.jpatutorial.id.id_sequence.UserSequence;
import vn.techmaster.jpatutorial.id.id_sequence.UserSequenceRepository;

@SpringBootTest
public class UserSequenceTest {

    @Autowired
    private UserSequenceRepository repo;

    @Test
    void saveUserSequence() {
        UserSequence userSequence = new UserSequence();
        userSequence.setName("Bùi Hiên");

        repo.save(userSequence);
    }
}
/*
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
        user_sequence
        (name, id)
    values
        (?, ?)
* */

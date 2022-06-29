package com.example.projection;

import com.example.projection.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    @Autowired
    private EntityManager em;

    @Test
    void get_all_user_test() {
        TypedQuery<User> typedQuery = em.createQuery("SELECT u from User u", User.class);
        List<User> users = typedQuery.getResultList();

        users.forEach(System.out::println);

        Assertions.assertThat(users.size()).isEqualTo(4);
        Assertions.assertThat(users).isNotNull();
    }

    @Test
    void find_user_by_id_return_user_test() {
        TypedQuery<User> typedQuery = em.createQuery("SELECT u from User u where u.id = ?1", User.class);
        typedQuery.setParameter(1, 1);

        User user = typedQuery.getSingleResult();
        System.out.println(user);

        Assertions.assertThat(user.getName()).isEqualTo("Bui Hien");
        Assertions.assertThat(user).isNotNull();
    }
}

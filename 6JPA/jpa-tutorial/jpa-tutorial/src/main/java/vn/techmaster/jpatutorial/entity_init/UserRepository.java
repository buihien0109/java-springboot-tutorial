package vn.techmaster.jpatutorial.entity_init;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.jpatutorial.entity_init.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
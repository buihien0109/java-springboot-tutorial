package vn.techmaster.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.course.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
}
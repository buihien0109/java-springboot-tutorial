package vn.techmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
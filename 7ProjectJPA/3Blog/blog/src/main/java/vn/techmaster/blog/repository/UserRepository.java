package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.blog.custom_repository.BlogCustomRepository;
import vn.techmaster.blog.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    <T> T getUserById(Integer id, Class<T> type);
}
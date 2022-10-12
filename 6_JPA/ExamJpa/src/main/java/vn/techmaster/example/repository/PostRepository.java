package vn.techmaster.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.example.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
}
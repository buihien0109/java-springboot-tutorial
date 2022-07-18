package vn.techmaster.jpablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.jpablog.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
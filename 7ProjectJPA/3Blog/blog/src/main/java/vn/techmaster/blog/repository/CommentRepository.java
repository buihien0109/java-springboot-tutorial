package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.techmaster.blog.dto.CommentInfo;
import vn.techmaster.blog.entity.Comment;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<CommentInfo> getCommentsByBlogId(@Param("id") String id);

    @Query("select c from Comment c")
    List<CommentInfo> getComments();
}
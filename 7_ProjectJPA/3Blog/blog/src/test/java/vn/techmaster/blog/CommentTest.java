package vn.techmaster.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blog.dto.CommentInfo;
import vn.techmaster.blog.repository.CommentRepository;

import java.util.List;

@SpringBootTest
public class CommentTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void get_comments_info_by_blog_id() {
        List<CommentInfo> rs = commentRepository.getCommentsByBlogId("IAU");
        rs.forEach(c -> System.out.println(c.getCreatedAt()));
    }
}

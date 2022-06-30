package vn.techmaster.jpablog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import vn.techmaster.jpablog.entity.Blog;
import vn.techmaster.jpablog.repository.BlogRepository;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BlogTest {

    @Autowired
    private BlogRepository blogRepository;

    @Test
    void get_all_blog_by_created_at_desc_test() {
        List<Blog> blogs = blogRepository.getByOrderByCreatedAtDesc();
        blogs.forEach(System.out::println);
    }

    @Test
    void get_top5_blog_by_pulished_at_desc_test() {
        List<Blog> blogs = blogRepository.findByOrderByPulishedAtDesc(PageRequest.of(0, 5));
        blogs.forEach(System.out::println);
    }
}

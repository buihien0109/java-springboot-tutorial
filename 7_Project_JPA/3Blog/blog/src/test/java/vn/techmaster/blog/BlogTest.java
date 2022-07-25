package vn.techmaster.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blog.dto.BlogDetail;
import vn.techmaster.blog.dto.BlogDto;
import vn.techmaster.blog.dto.BlogInfo;
import vn.techmaster.blog.repository.BlogRepository;

import java.util.List;

@SpringBootTest
public class BlogTest {

    @Autowired
    private BlogRepository blogRepository;

    @Test
    void get_all_blog_info() {
        List<BlogInfo> blogs = blogRepository.getAllBlogInfo();
        blogs.forEach(System.out::println);
    }

    @Test
    void get_blog_detail_by_id() {
        BlogDetail blog = blogRepository.getBlogDetailById("1tS");
        System.out.println(blog);
    }
}

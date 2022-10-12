package vn.techmaster.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import vn.techmaster.example.entity.Post;
import vn.techmaster.example.repository.PostRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @Rollback(value = false)
    void save_post_test() {
        Post post = new Post();
        postRepository.save(post);
    }
}

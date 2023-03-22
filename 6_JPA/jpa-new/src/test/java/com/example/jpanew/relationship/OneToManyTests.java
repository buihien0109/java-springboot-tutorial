package com.example.jpanew.relationship;

import com.example.jpanew.one_to_many.Author;
import com.example.jpanew.one_to_many.AuthorRepository;
import com.example.jpanew.one_to_many.Post;
import com.example.jpanew.one_to_many.PostRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class OneToManyTests {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void save_blog_author() {
        Faker faker = new Faker();
        for (int i = 0; i < 3; i++) {
            List<Post> posts = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                Post post = Post.builder()
                        .title(faker.company().name())
                        .build();
                posts.add(post);
            }

            Author author = Author.builder()
                    .name(faker.name().fullName())
                    .posts(posts)
                    .build();

            authorRepository.save(author);
        }
    }

    @Test
    void get_auth() {
        Author author = authorRepository.findById(1)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found author");
                });
        System.out.println(author);
    }

    @Test
    void get_post() {
        Post post = postRepository.findById(2)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found post");
                });
        System.out.println(post);
    }

    @Test
    void delete_auth() {
        authorRepository.deleteById(4);
    }

    @Test
    void delete_post() {
        postRepository.deleteById(8);
    }
}

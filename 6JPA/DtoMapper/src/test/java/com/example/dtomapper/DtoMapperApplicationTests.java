package com.example.dtomapper;

import com.example.dtomapper.model.Blog;
import com.example.dtomapper.repository.AuthorRepository;
import com.example.dtomapper.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class DtoMapperApplicationTests {


    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AuthorRepository authorRepository;

//    @Test
//    void test_getBlogById() {
//        Optional<Blog> blogOptional = blogRepository.findById(1);
//        if(blogOptional.isPresent()) {
//            Blog blog = blogOptional.get();
//
//        }
//    }

}

package com.example.projection;

import com.example.projection.entity.Author;
import com.example.projection.entity.Book;
import com.example.projection.projection.AuthorInfo;
import com.example.projection.projection.BookInfo;
import com.example.projection.repository.AuthorRepository;
import com.example.projection.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AuthorTest {

    @Autowired
    private AuthorRepository authorRepository;


    @Test
    void find_book_by_id_return_book_info_test() {
        AuthorInfo author = authorRepository.getAuthorById(1, AuthorInfo.class);
        author.showInfo();

        Assertions.assertThat(author).isNotNull();
    }

    @Test
    void find_book_by_id_return_book_test() {
        Author author = authorRepository.getAuthorById(1, Author.class);
        System.out.println(author);

        Assertions.assertThat(author).isNotNull();
    }
}

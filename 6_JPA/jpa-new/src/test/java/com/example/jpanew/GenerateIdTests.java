package com.example.jpanew;

import com.example.jpanew.generateId.Book;
import com.example.jpanew.generateId.BookRepository;
import com.example.jpanew.generateId.Comic;
import com.example.jpanew.generateId.ComicRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GenerateIdTests {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ComicRepository comicRepository;


    @Test
    @Rollback(value = false)
    void save_book() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Book book = new Book(faker.book().title());
            bookRepository.save(book);
        }
    }

    @Test
    @Rollback(value = false)
    void save_comic() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Comic comic = new Comic(faker.book().title());
            comicRepository.save(comic);
        }
    }
}

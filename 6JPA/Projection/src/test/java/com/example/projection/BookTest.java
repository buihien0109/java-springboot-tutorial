package com.example.projection;

import com.example.projection.entity.Author;
import com.example.projection.entity.Book;
import com.example.projection.projection.BookInfo;
import com.example.projection.repository.AuthorRepository;
import com.example.projection.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BookTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void insert_book_test() {
        Book book = new Book("Sách 1", "Mô tả 1", 100);
        Book book1 = new Book("Sách 2", "Mô tả 2", 101);
        Book book2 = new Book("Sách 3", "Mô tả 3", 102);
        Book book3 = new Book("Sách 4", "Mô tả 4", 103);
        Book book4 = new Book("Sách 5", "Mô tả 5", 104);
        Book book5 = new Book("Sách 6", "Mô tả 6", 105);

        Author author = new Author("Bùi Hiên", "hien@gmail.com");
        Author author1 = new Author("Phương Loan", "loan@gmail.com");

        author.addBook(book);
        author.addBook(book1);
        author.addBook(book2);
        author.addBook(book3);

        author1.addBook(book4);
        author1.addBook(book5);

        authorRepository.save(author);
        authorRepository.save(author1);
    }

    @Test
    void find_book_by_id_return_book_info_test() {
        BookInfo book = bookRepository.getBookById(1, BookInfo.class);
        book.showInfo();

        Assertions.assertThat(book).isNotNull();
    }

    @Test
    void find_book_by_id_return_book_test() {
        Book book = bookRepository.getBookById(1, Book.class);
        System.out.println(book);

        Assertions.assertThat(book).isNotNull();
    }
}

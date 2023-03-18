package com.example.jpanew.generateId;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(generator = "book-generator")
    @GenericGenerator(name = "book-generator",
            strategy = "com.example.jpanew.generateId.BookGenerateId")
    private String id;

    private String name;

    public Book(String name) {
        this.name = name;
    }
}
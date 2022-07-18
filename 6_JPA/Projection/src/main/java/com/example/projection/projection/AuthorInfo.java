package com.example.projection.projection;

import java.util.List;

public interface AuthorInfo {
    Integer getId();

    String getName();

    List<BookInfo> getBooks();

    interface BookInfo {
        Integer getId();

        String getTitle();
    }

    default void showInfo() {
        System.out.println(getId() + " - " + getName());
    }
}

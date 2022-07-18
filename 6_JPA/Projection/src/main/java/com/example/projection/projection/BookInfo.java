package com.example.projection.projection;

public interface BookInfo {
    Integer getId();

    String getTitle();

    String getDescription();

    AuthorInfo getAuthor();

    interface AuthorInfo {
        Integer getId();

        String getName();
    }

    default void showInfo() {
        System.out.println(getId() + " - " + getTitle() + " - " + getDescription() + " - {" + getAuthor().getId() + " - " + getAuthor().getName() + "}");
    }
}

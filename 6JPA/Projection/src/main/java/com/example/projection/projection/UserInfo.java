package com.example.projection.projection;

public interface UserInfo {
    Integer getId();

    String getName();

    String getEmail();

    default void showInfo() {
        System.out.println(getId() + " - " + getName() + " - " + getEmail());
    }
}

package com.example.jobhuntbackend.model;

public enum Skill {
    Java("Java"),
    Golang("Golang"),
    AWS("AWS"),
    SQL("SQL"),
    React("React"),
    PHP("PHP");

    public final String label;

    Skill(String label) {
        this.label = label;
    }
}

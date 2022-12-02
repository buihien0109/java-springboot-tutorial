package com.example.basic;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class BasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

    @Bean
    public Slugify slugify() {
        return Slugify.builder().customReplacement("đ", "d").build();
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public Random random() {
        return new Random();
    }
}

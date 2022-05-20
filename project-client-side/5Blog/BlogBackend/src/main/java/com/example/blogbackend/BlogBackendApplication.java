package com.example.blogbackend;

import com.github.slugify.Slugify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class BlogBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogBackendApplication.class, args);
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public Slugify slugify() {
        return new Slugify();
    }

}

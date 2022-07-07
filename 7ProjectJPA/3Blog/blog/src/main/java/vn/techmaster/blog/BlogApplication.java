package vn.techmaster.blog;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public Slugify slugify() {
        return new Slugify();
    }
    @Bean
    public Random random() {
        return new Random();
    }
}

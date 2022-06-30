package vn.techmaster.jpablog;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpablogApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpablogApplication.class, args);
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public Slugify slugify() {
        return new Slugify();
    }
}

package com.example.jpanew.relationship;

import com.example.jpanew.one_to_one.DeveloperRepository;
import com.example.jpanew.one_to_one.Identity;
import com.example.jpanew.one_to_one.Developer;
import com.example.jpanew.one_to_one.IdentityRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class OneToOneTests {
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private IdentityRepository identityRepository;

    @Test
    void save_user_identity() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Developer developer = Developer.builder()
                    .name(faker.name().fullName())
                    .identity(
                            Identity.builder()
                                    .code(String.valueOf(faker.number().numberBetween(1000, 9000)))
                                    .build()
                    )
                    .build();

            developerRepository.save(developer);
        }
    }

    @Test
    void get_developer() {
        Developer developer = developerRepository.findById(2)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found developer");
                });
        System.out.println(developer);
    }

    @Test
    void get_identity() {
        Identity identity = identityRepository.findById(2)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found identity");
                });
        System.out.println(identity);
    }

    @Test
    void delete_developer() {
        developerRepository.deleteById(4);
    }

    @Test
    void delete_identity() {
        identityRepository.deleteById(8);
    }
}

package com.example.jpanew;

import com.example.jpanew.createId.Person;
import com.example.jpanew.createId.PersonRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonTests {
    @Autowired
    private PersonRepository personRepository;

    @Test
    @Rollback(value = false)
    void save_person() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Person person = new Person(faker.name().fullName());
            personRepository.save(person);
        }
    }
}

package com.example.jpanew;

import com.example.jpanew.generateId.Book;
import com.example.jpanew.save_json.Brand;
import com.example.jpanew.save_json.Laptop;
import com.example.jpanew.save_json.LaptopRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LaptopTests {

    @Autowired
    private LaptopRepository laptopRepository;

    @Test
    @Rollback(value = false)
    void save_laptop() {
        Faker faker = new Faker();
        Random rd = new Random();
        for (int i = 0; i < 20; i++) {
            List<String> categoies = new ArrayList<>();
            int count = rd.nextInt(4 - 2 + 1) + 2;
            for (int j = 0; j < count; j++) {
                categoies.add(faker.leagueOfLegends().champion());
            }

            Brand brand = new Brand(
                    i + 1,
                    faker.company().name(),
                    faker.company().logo()
            );
            Laptop laptop = new Laptop(brand, categoies);
            laptopRepository.save(laptop);
        }
    }
}

package com.example.jpanew;

import com.example.jpanew.createId.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTests {
    @Autowired
    private ProductZeroRepository productZeroRepository;

    @Autowired
    private ProductOneRepository productOneRepository;

    @Autowired
    private ProductTwoRepository productTwoRepository;

    @Autowired
    private ProductThreeRepository productThreeRepository;

    @Autowired
    private ProductFourRepository productFourRepository;

    @Test
    @Rollback(value = false)
    void save_productZero() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Product.ProductZero productZero = new Product.ProductZero(faker.name().fullName());
            productZeroRepository.save(productZero);
        }
    }

    @Test
    @Rollback(value = false)
    void save_productOne() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Product.ProductOne productOne = new Product.ProductOne(faker.name().fullName());
            productOneRepository.save(productOne);
        }
    }

    @Test
    @Rollback(value = false)
    void save_productTwo() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Product.ProductTwo productTwo = new Product.ProductTwo(faker.name().fullName());
            productTwoRepository.save(productTwo);
        }
    }

    @Test
    @Rollback(value = false)
    void save_productThree() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Product.ProductThree productThree = new Product.ProductThree(faker.name().fullName());
            productThreeRepository.save(productThree);
        }
    }

    @Test
    @Rollback(value = false)
    void save_productFour() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Product.ProductFour productFour = new Product.ProductFour(faker.name().fullName());
            productFourRepository.save(productFour);
        }
    }
}

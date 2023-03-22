package com.example.jpanew;

import com.example.jpanew.customRepository.Customer;
import com.example.jpanew.customRepository.CustomerRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CustomerTests {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void save_customer() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Customer customer = new Customer(
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().cellPhone(),
                    "111"
            );
            customerRepository.save(customer);
        }
    }

    @Test
    void findByNameStartsWith_test() {
        List<Customer> list = customerRepository.findByNameStartsWith("a");
        list.forEach(System.out::println);
    }
}

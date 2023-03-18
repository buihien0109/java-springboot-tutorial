package com.example.jpanew;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.example.jpanew.compositeId.*;
import com.example.jpanew.compositeIdGenerate.Employee;
import com.example.jpanew.compositeIdGenerate.EmployeeRepository;
import com.example.jpanew.generateId.Book;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompositeIdTests {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @Rollback(value = false)
    void save_order() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            OrderIds ids = new OrderIds(
                    faker.number().numberBetween(1, 100),
                    faker.number().numberBetween(1, 100)
            );
            Order order = new Order(ids, faker.number().numberBetween(1_000, 2_000));
            orderRepository.save(order);
        }
    }

    @Test
    @Rollback(value = false)
    void save_employee() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Employee employee = new Employee(faker.number().numberBetween(1_000, 2_000));
            employeeRepository.save(employee);
        }
    }

    @Test
    @Rollback(value = false)
    void save_teacher() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Teacher teacher = new Teacher(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress()
            );
            teacherRepository.save(teacher);
        }
    }
}

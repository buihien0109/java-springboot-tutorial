package com.example.jpanew;

import com.example.jpanew.query.AddressStaff;
import com.example.jpanew.query.Staff;
import com.example.jpanew.query.StaffRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StaffTests {
    @Autowired
    private StaffRepository staffRepository;

    @Test
    @Rollback(value = false)
    void save_staff() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Staff staff = Staff.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .password("111")
                    .addressStaff(
                            new AddressStaff(
                                    faker.address().cityName(),
                                    faker.address().secondaryAddress()
                            )

                    )
                    .build();

            staffRepository.save(staff);
        }
    }
}

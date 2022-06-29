package com.example.relationship;

import com.example.relationship.model.one_to_many.unidrection.Person;
import com.example.relationship.model.one_to_many.unidrection.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OneToMany_UniDirection_Test {
    @Autowired
    private EntityManager em;

    @Test
    void save_data_test() {
        Person person = new Person();
        Phone phone1 = new Phone("123-456-7890");
        Phone phone2 = new Phone("321-654-0987");

        person.getPhones().add(phone1);
        person.getPhones().add(phone2);
        em.persist(person);

        person.getPhones().remove(phone1);
        em.flush();
    }
}

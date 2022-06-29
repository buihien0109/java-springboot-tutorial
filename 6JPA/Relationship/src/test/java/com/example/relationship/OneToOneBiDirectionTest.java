package com.example.relationship;

import com.example.relationship.model.one_to_one.bidrection.Card;
import com.example.relationship.model.one_to_one.bidrection.Student;
import com.example.relationship.repository.CardRepository;
import com.example.relationship.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OneToOneBiDirectionTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CardRepository cardRepository;


    @Test
    void add_data() {
        studentRepository.deleteAll();

        Student student = new Student("Bùi Hiên");
        Student student1 = new Student("Phương Loan");

        Card card = new Card("111");
        Card card1 = new Card("222");

        student.addCard(card);
        student1.addCard(card1);

        studentRepository.save(student);
        studentRepository.save(student1);
    }

    @Test
    void count_student_and_card() {
        long countStudent = studentRepository.count();
        long countCard = cardRepository.count();

        assertThat(countStudent).isEqualTo(2L);
        assertThat(countCard).isEqualTo(2L);
    }

    @Test
    void get_card_by_code() {
        Card card = cardRepository.findByCode("111");

        Assertions.assertThat(card).isNotNull();
    }

    @Test
    void get_student_by_name() {
        Student student = studentRepository.findByName("Bùi Hiên");
        System.out.println(student);

        System.out.println(student.getCard());
    }
}

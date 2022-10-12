package vn.techmaster.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import vn.techmaster.example.entity.Category;
import vn.techmaster.example.repository.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Rollback(value = false)
    void delete_category() {
         categoryRepository.deleteById(4L);
    }

    @Test
    void get_by_id_test() {
        Category category = categoryRepository.getById(5L);
        System.out.println(category);
    }
}

package vn.techmaster.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blog.entity.Category;
import vn.techmaster.blog.repository.CategoryRepository;

import java.util.List;

@SpringBootTest
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void get_categories_most_papular_test() {
        List<Category> categories = categoryRepository.getCategoriesPopular(5);
        categories.forEach(System.out::println);
    }
}

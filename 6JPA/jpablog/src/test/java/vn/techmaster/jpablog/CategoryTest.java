package vn.techmaster.jpablog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import vn.techmaster.jpablog.model.dto.CategoryInfo;
import vn.techmaster.jpablog.repository.CategoryRepository;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void get_category_name_and_product_count() {
        List<CategoryInfo> categoryInfos = categoryRepository.getCategoryNameAndBlogCount();
        categoryInfos.forEach(System.out::println);
    }
}

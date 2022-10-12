package vn.techmaster.example;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import vn.techmaster.example.entity.Category;
import vn.techmaster.example.entity.Product;
import vn.techmaster.example.entity.Tag;
import vn.techmaster.example.repository.CategoryRepository;
import vn.techmaster.example.repository.ProductRepository;
import vn.techmaster.example.repository.TagRepository;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private Faker faker;

    @Autowired
    private Random rd;

    @Test
    @Rollback(value = false)
    void save_tag() {
        for (int i = 0; i < 5; i++) {
            Tag tag = new Tag(null, faker.leagueOfLegends().rank());
            tagRepository.save(tag);
        }
    }

    @Test
    @Rollback(value = false)
    void save_category() {
        for (int i = 0; i < 5; i++) {
            Category category = new Category(faker.leagueOfLegends().champion());
            categoryRepository.save(category);
        }
    }

    @Test
    @Rollback(value = false)
    void save_product() {
        List<Tag> tags = tagRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        for (int i = 0; i < 15; i++) {
            Category rdCategory = categories.get(rd.nextInt(categories.size()));

            Set<Tag> rdTagList = new HashSet<>();
            for (int j = 0; j < 3; j++) {
                Tag rdTag = tags.get(rd.nextInt(tags.size()));
                rdTagList.add(rdTag);
            }

            Product product = new Product(null, faker.name().fullName(), rdCategory, rdTagList);
            productRepository.save(product);
        }
    }

    @Test
    void get_by_id_test() {
        Product product = productRepository.getById(14L);
        System.out.println(product);
        System.out.println(product.getCategory());
    }
}

package vn.techmaster.jpablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.techmaster.jpablog.entity.Category;
import vn.techmaster.jpablog.model.dto.CategoryInfo;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(name = "getCategoryNameAndBlogCountt", nativeQuery = true)
    List<CategoryInfo> getCategoryNameAndBlogCount();
}
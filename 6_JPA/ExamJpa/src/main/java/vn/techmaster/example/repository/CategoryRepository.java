package vn.techmaster.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.example.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getById(Long id);
}